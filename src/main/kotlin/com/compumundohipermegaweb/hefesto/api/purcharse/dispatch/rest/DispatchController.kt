package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.ConfirmDispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.DispatchOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.FindAllDispatches
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.FullDispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchError
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchedItem
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation.*
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.rest.representation.PurchaseOrderResponse
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.SupplierResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/dispatches")
class DispatchController(private val dispatchOrders: DispatchOrders,
                         private val findAllDispatches: FindAllDispatches,
                         private val confirmDispatch: ConfirmDispatch) {

    @PostMapping
    fun post(@RequestBody request: DispatchRequest): ResponseEntity<DispatchResponse> {
        val actionData = DispatchOrders.ActionData(
                dispatchId = request.dispatchId,
                supplierId = request.supplierId,
                totalCost = request.totalCost,
                dispatchedItems = request.dispatchedItems.items.map { it.toDispatchedItem() })
        val result = dispatchOrders(actionData)

        return ResponseEntity.ok(DispatchResponse(
                status = result.status.name,
                errors = result.errors.map { it.toResponse() }))
    }

    @GetMapping
    fun getAll(): ResponseEntity<GetAllDispatchesResponse> {
        val dispatches = findAllDispatches().map { it.toResponse() }
        return ResponseEntity.ok(GetAllDispatchesResponse(dispatches))
    }

    @PostMapping("/{ID}/confirm")
    fun confirm(@PathVariable("ID") id: Long, @RequestBody request: ConfirmDispatchRequest): ResponseEntity<Any> {
        val actionData = ConfirmDispatch.ActionData(id, request.branchId, request.totalCost)
        confirmDispatch(actionData)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    private fun DispatchedItemRequest.toDispatchedItem() = DispatchedItem(sku, amount, unitPrice, subtotal)

    private fun DispatchError.toResponse() = DispatchErrorResponse(code.name, dispatchedItem.toResponse())

    private fun DispatchedItem.toResponse() = DispatchedItemResponse(sku, amount, unitPrice, subtotal)

    private fun FullDispatch.toResponse() = FullDispatchResponse(id, supplier.toResponse(), totalCost, status.name, purchaseOrders.map { it.toResponse() })

    private fun Supplier.toResponse() = SupplierResponse(id, organization, contactName, contactNumber, email, cuit)

    private fun PurchaseOrder.toResponse() = PurchaseOrderResponse(id, branchId, sku, requested, supplier, status.name, dispatchId)
}
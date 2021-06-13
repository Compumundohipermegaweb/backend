package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.DispatchOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchError
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchedItem
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dispatches")
class DispatchController(private val dispatchOrders: DispatchOrders) {

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

    private fun DispatchedItemRequest.toDispatchedItem() = DispatchedItem(sku, amount, unitPrice, subtotal)

    private fun DispatchError.toResponse() = DispatchErrorResponse(code.name, dispatchedItem.toResponse())

    private fun DispatchedItem.toResponse() = DispatchedItemResponse(sku, amount, unitPrice, subtotal)
}
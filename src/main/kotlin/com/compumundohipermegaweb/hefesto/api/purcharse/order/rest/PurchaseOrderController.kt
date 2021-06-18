package com.compumundohipermegaweb.hefesto.api.purcharse.order.rest

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.action.FindAllPurchaseOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.rest.representation.GetAllPurchaseOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.order.rest.representation.PurchaseOrderResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/purchase-orders")
class PurchaseOrderController(private val findAllPurchaseOrders: FindAllPurchaseOrders) {

    @GetMapping
    fun getAll(): ResponseEntity<GetAllPurchaseOrders> {
        val purchaseOrders = findAllPurchaseOrders().map { it.toResponse() }
        return ResponseEntity.ok(GetAllPurchaseOrders(purchaseOrders))
    }

    private fun PurchaseOrder.toResponse() = PurchaseOrderResponse(id, branchId, sku, requested, supplier, status.name, dispatchId)
}
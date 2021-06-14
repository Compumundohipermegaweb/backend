package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository

class FindAllPurchaseOrders(private val purchaseOrderRepository: PurchaseOrderRepository) {

    operator fun invoke(): List<PurchaseOrder> {
        return purchaseOrderRepository.findAll()
    }
}
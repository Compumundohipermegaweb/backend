package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder

interface PurchaseOrderRepository {

    fun save(purchaseOrder: PurchaseOrder): PurchaseOrder
    fun exists(sku: String): Boolean
    fun accept(id: Long, dispatchId: Long)
    fun findBySku(sku: String): PurchaseOrder?
    fun findAll(): List<PurchaseOrder>
}

package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PurchaseOrderRepresentation

class JpaPurchaseOrderRepository(private val purchaseOrderDao: PurchaseOrderDao): PurchaseOrderRepository {

    override fun save(purchaseOrder: PurchaseOrder): PurchaseOrder {
        val saved = purchaseOrderDao.save(purchaseOrder.toRepresentation())
        return saved.toPurchaseOrder()
    }

    override fun exists(sku: String): Boolean {
        return purchaseOrderDao.existsBySku(sku)
    }

    override fun acceptById(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findBySku(sku: String): PurchaseOrder? {
        TODO("Not yet implemented")
    }

    private fun PurchaseOrder.toRepresentation() = PurchaseOrderRepresentation(id, sku, amount, supplier)

    private fun PurchaseOrderRepresentation.toPurchaseOrder() = PurchaseOrder(id, sku, amount, supplier)
}

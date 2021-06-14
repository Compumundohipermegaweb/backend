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

    override fun accept(id: Long, dispatchId: Long) {
        purchaseOrderDao.updateStatus(id, PurchaseOrder.Status.ACCEPTED.name)
        purchaseOrderDao.updateDispatchId(id, dispatchId)
    }

    override fun findBySku(sku: String): PurchaseOrder? {
        val representation = purchaseOrderDao.findBySku(sku)
        return representation?.toPurchaseOrder()
    }

    private fun PurchaseOrder.toRepresentation() = PurchaseOrderRepresentation(id, branchId, sku, amount, supplier, status.name, dispatchId)

    private fun PurchaseOrderRepresentation.toPurchaseOrder() = PurchaseOrder(id, branchId, sku, amount, supplier, PurchaseOrder.Status.valueOf(status), dispatchId)
}

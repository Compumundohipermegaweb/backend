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
        return purchaseOrderDao.existsBySkuAndStatus(sku, PurchaseOrder.Status.PENDING.name)
                || purchaseOrderDao.existsBySkuAndStatus(sku, PurchaseOrder.Status.ACCEPTED.name)
    }

    override fun accept(id: Long, dispatchId: Long, dispatchedAmount: Int, cost: Double) {
        purchaseOrderDao.updateStatus(id, PurchaseOrder.Status.ACCEPTED.name)
        purchaseOrderDao.updateDispatchId(id, dispatchId)
        purchaseOrderDao.updateDispatchedAmount(id, dispatchedAmount)
        purchaseOrderDao.updateCost(id, cost)
    }

    override fun findBySku(sku: String): PurchaseOrder? {
        val representation = purchaseOrderDao.findBySku(sku)
        return representation?.toPurchaseOrder()
    }

    override fun findAll(): List<PurchaseOrder> {
        val representations = purchaseOrderDao.findAll()
        return representations.map { it.toPurchaseOrder() }
    }

    override fun findByDispatchId(dispatchId: Long): List<PurchaseOrder> {
        val representations = purchaseOrderDao.findAllByDispatchId(dispatchId)
        return representations.map { it.toPurchaseOrder() }
    }

    override fun confirmByDispatchId(dispatchId: Long) {
        purchaseOrderDao.updateStatusByDispatchId(dispatchId, PurchaseOrder.Status.CONFIRMED.name)
    }

    private fun PurchaseOrder.toRepresentation() = PurchaseOrderRepresentation(id, branchId, sku, requested, dispatched, cost, supplier, status.name, dispatchId)

    private fun PurchaseOrderRepresentation.toPurchaseOrder() = PurchaseOrder(id, branchId, sku, requested, dispatched, cost, supplier, PurchaseOrder.Status.valueOf(status), dispatchId)
}

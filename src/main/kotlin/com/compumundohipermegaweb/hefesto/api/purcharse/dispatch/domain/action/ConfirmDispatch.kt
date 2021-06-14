package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository

class ConfirmDispatch(private val dispatchRepository: DispatchRepository,
                      private val purchaseOrderRepository: PurchaseOrderRepository,
                      private val stockRepository: StockRepository) {

    operator fun invoke(dispatchId: Long) {
        dispatchRepository.confirm(dispatchId)
        purchaseOrderRepository.confirmByDispatchId(dispatchId)
        val purchaseOrders = purchaseOrderRepository.findByDispatchId(dispatchId)
        purchaseOrders.forEach {
            stockRepository.increaseStock(it.sku, it.amount)
        }
    }
}
package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.service.CashService
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository

class ConfirmDispatch(private val dispatchRepository: DispatchRepository,
                      private val purchaseOrderRepository: PurchaseOrderRepository,
                      private val stockRepository: StockRepository,
                      private val cashService: CashService) {

    operator fun invoke(actionData: ActionData) {
        registerExpense(actionData.branchId, actionData.dispatchTotalCost, actionData.dispatchId)
        confirmDispatch(actionData.dispatchId)
        confirmPurchaseOrders(actionData.dispatchId)
        adjustStock(actionData.dispatchId)
    }

    private fun registerExpense(branchId: Long, totalCost: Double, dispatchId: Long) {
        cashService.registerSupplierExpense(branchId, totalCost, dispatchId)
    }

    private fun confirmDispatch(dispatchId: Long) {
        dispatchRepository.confirm(dispatchId)
    }

    private fun confirmPurchaseOrders(dispatchId: Long) {
        purchaseOrderRepository.confirmByDispatchId(dispatchId)
    }

    private fun adjustStock(dispatchId: Long) {
        val purchaseOrders = purchaseOrderRepository.findByDispatchId(dispatchId)
        purchaseOrders.forEach {
            stockRepository.increaseStock(it.sku, it.amount)
        }
    }

    data class ActionData(val dispatchId: Long, val branchId: Long, val dispatchTotalCost: Double)
}
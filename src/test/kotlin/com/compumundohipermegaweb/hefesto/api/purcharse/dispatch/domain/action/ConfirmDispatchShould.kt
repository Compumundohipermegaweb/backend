package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.service.CashService
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class ConfirmDispatchShould {

    private lateinit var dispatchRepository: DispatchRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var stockRepository: StockRepository
    private lateinit var cashService: CashService
    private lateinit var confirmDispatch: ConfirmDispatch

    @Test
    fun `set the status to CONFIRMED`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenCashService()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenDispatchIsConfirmed()
    }

    @Test
    fun `update the status of the purchase orders`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenCashService()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenPurchaseOrderStatusWhereUpdated()
    }

    @Test
    fun `update the stock`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenCashService()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenStockIsUpdated()
    }

    @Test
    fun `register the cash movement`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenCashService()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenExpenseHaveBeenRegistered()
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
        `when`(purchaseOrderRepository.findByDispatchId(ACTION_DATA.dispatchId)).thenReturn(listOf(PURCHASE_ORDER))
    }

    private fun givenStockRepository() {
        stockRepository = mock()
    }

    private fun givenCashService() {
        cashService = mock()
    }

    private fun givenConfirmDispatch() {
        confirmDispatch = ConfirmDispatch(dispatchRepository, purchaseOrderRepository, stockRepository, cashService)
    }

    private fun whenConfirmingDispatch() {
        confirmDispatch(ACTION_DATA)
    }

    private fun thenDispatchIsConfirmed() {
        verify(dispatchRepository).confirm(ACTION_DATA.dispatchId)
    }

    private fun thenPurchaseOrderStatusWhereUpdated() {
        verify(purchaseOrderRepository).confirmByDispatchId(ACTION_DATA.dispatchId)
    }

    private fun thenStockIsUpdated() {
        verify(stockRepository).increaseStock(PURCHASE_ORDER.sku, PURCHASE_ORDER.amount)
    }

    private fun thenExpenseHaveBeenRegistered() {
        verify(cashService).registerSupplierExpense(ACTION_DATA.branchId, ACTION_DATA.dispatchTotalCost, ACTION_DATA.dispatchId)
    }

    private companion object {
        val ACTION_DATA = ConfirmDispatch.ActionData(1L, 1L, 100.0)
        val PURCHASE_ORDER = PurchaseOrder(1L,1L, "1", 1, "", PurchaseOrder.Status.CONFIRMED, 1L)
    }
}

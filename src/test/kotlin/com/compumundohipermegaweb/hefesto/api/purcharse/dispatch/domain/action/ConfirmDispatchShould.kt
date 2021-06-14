package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

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
    private lateinit var confirmDispatch: ConfirmDispatch

    @Test
    fun `set the status to CONFIRMED`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenDispatchIsConfirmed()
    }

    @Test
    fun `update the status of the purchase orders`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        verify(purchaseOrderRepository).confirmByDispatchId(1L)
    }

    @Test
    fun `update the stock`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenStockRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenStockIsUpdated()
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
        `when`(purchaseOrderRepository.findByDispatchId(1L)).thenReturn(listOf(PURCHASE_ORDER))
    }

    private fun givenStockRepository() {
        stockRepository = mock()
    }

    private fun givenConfirmDispatch() {
        confirmDispatch = ConfirmDispatch(dispatchRepository, purchaseOrderRepository, stockRepository)
    }

    private fun whenConfirmingDispatch() {
        confirmDispatch(1L)
    }

    private fun thenDispatchIsConfirmed() {
        verify(dispatchRepository).confirm(1L)
    }

    private fun thenStockIsUpdated() {
        verify(stockRepository).increaseStock(PURCHASE_ORDER.sku, PURCHASE_ORDER.amount)
    }

    private companion object {
        val PURCHASE_ORDER = PurchaseOrder(1L,1L, "1", 1, "", PurchaseOrder.Status.CONFIRMED, 1L)
    }
}

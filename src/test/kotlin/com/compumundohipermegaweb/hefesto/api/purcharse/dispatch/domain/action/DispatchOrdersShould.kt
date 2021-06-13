package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchError
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchOrdersResult
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchedItem
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DispatchOrdersShould {

    private lateinit var dispatchRepository: DispatchRepository
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var itemRepository: ItemRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var priceToleranceRepository: PriceToleranceRepository
    private lateinit var dispatchOrders: DispatchOrders

    private lateinit var result: DispatchOrdersResult

    @Test
    fun `save the dispatch`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        whenDispatchingOrders()

        thenDispatchIsSaved()
    }

    @Test
    fun `reject the dispatch if supplier does not exists`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        result = dispatchOrders(DispatchOrders.ActionData(1L, UNKNOWN_SUPPLIER, 0.0, emptyList()))

        thenDispatchIsRejected()
    }

    @Test
    fun `reject purchase orders with unknown skus`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        val actionData = DispatchOrders.ActionData(
                2L,
                1L,
                20.0,
                dispatchedItems = listOf(
                        DispatchedItem(UNKNOWN_SKU, 1, 15.0, 15.0),
                        DispatchedItem(SKU, 1, 5.0, 5.0)))
        result = dispatchOrders(actionData)

        thenResultHasError(DispatchError(DispatchError.Code.UNKNOWN_SKU, actionData.dispatchedItems[0]))
    }

    @Test
    fun `reject dispatched items without purchase order`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        val actionData = DispatchOrders.ActionData(
                2L,
                1L,
                20.0,
                dispatchedItems = listOf(
                        DispatchedItem(SKU_WITHOUT_PURCHASE_ORDER, 1, 15.0, 15.0),
                        DispatchedItem(SKU, 1, 5.0, 5.0)))
        result = dispatchOrders(actionData)

        thenResultHasError(DispatchError(DispatchError.Code.NO_PURCHASE_ORDER, actionData.dispatchedItems[0]))
    }

    @Test
    fun `reject dispatcher items when the price is to high`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        val actionData = DispatchOrders.ActionData(
                2L,
                1L,
                205.0,
                dispatchedItems = listOf(
                        DispatchedItem(EXPENSIVE_SKU, 1, 200.0, 200.0),
                        DispatchedItem(SKU, 1, 5.0, 5.0)))
        result = dispatchOrders(actionData)

        thenResultHasError(DispatchError(DispatchError.Code.PRICE_DIFFERENCE_TOO_HIGH, actionData.dispatchedItems[0]))
    }

    @Test
    fun `update the purchase orders status`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenItemRepository()
        givenPurchaseOrderRepository()
        givenPriceToleranceRepository()
        givenDispatchOrders()

        val actionData = DispatchOrders.ActionData(
                2L,
                1L,
                205.0,
                dispatchedItems = listOf(
                        DispatchedItem(SKU, 1, 5.0, 5.0)))
        result = dispatchOrders(actionData)

        verify(purchaseOrderRepository).acceptById(PURCHASE_ORDER_ID)
    }

    private fun thenResultHasError(dispatchError: DispatchError) {
        then(result.errors).containsExactly(dispatchError)
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock()
        `when`(supplierRepository.findById(ACTION_DATA.supplierId)).thenReturn(mock())
        `when`(supplierRepository.findById(UNKNOWN_SUPPLIER)).thenReturn(null)
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
    }

    private fun givenItemRepository() {
        itemRepository = mock()
        `when`(itemRepository.findBySku(UNKNOWN_SKU)).thenReturn(null)
        `when`(itemRepository.findBySku(SKU)).thenReturn(ITEM)
        `when`(itemRepository.findBySku(SKU_WITHOUT_PURCHASE_ORDER)).thenReturn(mock())
        `when`(itemRepository.findBySku(EXPENSIVE_SKU)).thenReturn(EXPENSIVE_ITEM)
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
        `when`(purchaseOrderRepository.exists(SKU)).thenReturn(true)
        `when`(purchaseOrderRepository.exists(SKU_WITHOUT_PURCHASE_ORDER)).thenReturn(false)
        `when`(purchaseOrderRepository.exists(EXPENSIVE_SKU)).thenReturn(true)
        `when`(purchaseOrderRepository.findBySku(SKU)).thenReturn(PURCHASE_ORDER)
    }

    private fun givenPriceToleranceRepository() {
        priceToleranceRepository = mock()
        `when`(priceToleranceRepository.find()).thenReturn(5)
    }

    private fun givenDispatchOrders() {
        dispatchOrders = DispatchOrders(dispatchRepository, supplierRepository, itemRepository, purchaseOrderRepository, priceToleranceRepository)
    }

    private fun whenDispatchingOrders() {
        dispatchOrders(ACTION_DATA)
    }

    private fun thenDispatchIsSaved() {
        verify(dispatchRepository).save(any())
    }

    private fun thenDispatchIsRejected() {
        verify(dispatchRepository).save(Dispatch(1L, UNKNOWN_SUPPLIER, 0.0, Dispatch.Status.REJECTED_UNKNOWN_SUPPLIER))
        then(result.status).isEqualTo(Dispatch.Status.REJECTED_UNKNOWN_SUPPLIER)
    }

    private companion object {
        const val UNKNOWN_SUPPLIER = 155L
        const val UNKNOWN_SKU = "999"
        const val SKU = "1"
        const val SKU_WITHOUT_PURCHASE_ORDER = "18976"
        const val EXPENSIVE_SKU = "111777"
        const val PURCHASE_ORDER_ID = 1L
        val PURCHASE_ORDER = PurchaseOrder(PURCHASE_ORDER_ID, SKU, 1, "", PurchaseOrder.Status.PENDING)
        val ACTION_DATA = DispatchOrders.ActionData(1L, 1L, 0.0, emptyList())
        val ITEM = Item(1L, SKU, "", "", 1L, 1L, "", 15.0, 5.0, false, "", 10)
        val EXPENSIVE_ITEM = Item(2L, EXPENSIVE_SKU, "", "", 1L, 1L, "", 150.0, 100.0, false, "", 10)
    }
}
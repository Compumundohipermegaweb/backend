package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PurchaseOrderRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaPurchaseOrderRepositoryShould {

    private lateinit var purchaseOrderDao: PurchaseOrderDao
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository

    private var purchaseOrder: PurchaseOrder? = null

    @Test
    fun `save a purchase order`() {
        givenPurchaseOrderDao()
        givenPurchaseOrderRepository()

        whenSavingPurchaseOrder()

        thenPurchaseOrderHasBeenSaved()
    }

    @Test
    fun `check if purchase order exist`() {
        givenPurchaseOrderDao()
        givenPurchaseOrderRepository()

        whenCheckingExistence()

        thenExistenceWasChecked()
    }

    @Test
    fun `accept by id`() {
        givenPurchaseOrderDao()
        givenPurchaseOrderRepository()

        whenAcceptingById()

        thenPurchaseOrderHasBeenAccepted()
    }

    @Test
    fun `find by sku`() {
        givenPurchaseOrderDao()
        givenPurchaseOrderRepository()

        whenFindingBySku()

        thenPurchaseOrderHasBeenFound()
    }

    private fun givenPurchaseOrderDao() {
        purchaseOrderDao = mock()
        `when`(purchaseOrderDao.save(PURCHASE_ORDER_REPRESENTATION)).thenReturn(PURCHASE_ORDER_REPRESENTATION)
        `when`(purchaseOrderDao.findBySku(SKU)).thenReturn(PURCHASE_ORDER_REPRESENTATION)
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = JpaPurchaseOrderRepository(purchaseOrderDao)
    }

    private fun whenSavingPurchaseOrder() {
        purchaseOrder = purchaseOrderRepository.save(PURCHASE_ORDER)
    }

    private fun whenCheckingExistence() {
        purchaseOrderRepository.exists(SKU)
    }

    private fun whenAcceptingById() {
        purchaseOrderRepository.accept(1L, 1L)
    }

    private fun whenFindingBySku() {
        purchaseOrder = purchaseOrderRepository.findBySku(SKU)
    }

    private fun thenPurchaseOrderHasBeenSaved() {
        verify(purchaseOrderDao).save(PURCHASE_ORDER_REPRESENTATION)
    }

    private fun thenExistenceWasChecked() {
        verify(purchaseOrderDao).existsBySkuAndStatus(SKU, PurchaseOrder.Status.PENDING.name)
        verify(purchaseOrderDao).existsBySkuAndStatus(SKU, PurchaseOrder.Status.ACCEPTED.name)
    }

    private fun thenPurchaseOrderHasBeenAccepted() {
        verify(purchaseOrderDao).updateStatus(1L, PurchaseOrder.Status.ACCEPTED.name)
        verify(purchaseOrderDao).updateDispatchId(1L, 1L)
    }

    private fun thenPurchaseOrderHasBeenFound() {
        verify(purchaseOrderDao).findBySku(SKU)
    }

    private companion object {
        const val SKU = "1"
        val PURCHASE_ORDER_REPRESENTATION = PurchaseOrderRepresentation(0L, 1L, "", 15, "", "PENDING", 0L)
        val PURCHASE_ORDER = PurchaseOrder(0L, 1L, "", 15, "", PurchaseOrder.Status.PENDING, 0L)
    }
}
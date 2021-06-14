package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class ConfirmDispatchShould {

    private lateinit var dispatchRepository: DispatchRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var confirmDispatch: ConfirmDispatch

    @Test
    fun `set the status to CONFIRMED`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenDispatchIsConfirmed()
    }

    @Test
    fun `update the status of the purchase orders`() {
        givenDispatchRepository()
        givenPurchaseOrderRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        verify(purchaseOrderRepository).confirmByDispatchId(1L)
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
    }

    private fun givenConfirmDispatch() {
        confirmDispatch = ConfirmDispatch(dispatchRepository, purchaseOrderRepository)
    }

    private fun whenConfirmingDispatch() {
        confirmDispatch(1L)
    }

    private fun thenDispatchIsConfirmed() {
        verify(dispatchRepository).confirm(1L)
    }
}
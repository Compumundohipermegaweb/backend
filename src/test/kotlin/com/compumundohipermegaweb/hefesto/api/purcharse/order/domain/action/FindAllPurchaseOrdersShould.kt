package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class FindAllPurchaseOrdersShould {

    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var findAllPurchaseOrders: FindAllPurchaseOrders
    
    @Test
    fun `find all the purchase orders`() {
        givenPurchaseOrderRepository()
        givenFindAllPurchaseOrders()

        whenFindingAllPurchaseOrders()

        thenAllPurchaseOrdersHaveBeenFound()
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
    }

    private fun givenFindAllPurchaseOrders() {
        findAllPurchaseOrders = FindAllPurchaseOrders(purchaseOrderRepository)
    }

    private fun whenFindingAllPurchaseOrders() {
        findAllPurchaseOrders()
    }

    private fun thenAllPurchaseOrdersHaveBeenFound() {
        verify(purchaseOrderRepository).findAll()
    }
}
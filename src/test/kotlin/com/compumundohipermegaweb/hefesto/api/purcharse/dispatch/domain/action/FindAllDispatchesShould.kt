package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class FindAllDispatchesShould {

    private lateinit var dispatchRepository: DispatchRepository
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var findAllDispatches: FindAllDispatches

    private lateinit var fullDispatches: List<FullDispatch>

    @Test
    fun `find all the existing dispatches`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenPurchaseOrderRepository()
        givenFindAllDispatches()

        whenFindingAllDispatches()

        thenDispatchesHaveBeenFound()
    }
    
    @Test
    fun `return the entire supplier`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenPurchaseOrderRepository()
        givenFindAllDispatches()

        whenFindingAllDispatches()

        thenSuppliersWhereLookedUp()
    }

    @Test
    fun `obtain all the related purchase orders`() {
        givenDispatchRepository()
        givenSupplierRepository()
        givenPurchaseOrderRepository()
        givenFindAllDispatches()

        whenFindingAllDispatches()

        thenPurchaseOrdersWhereRetrieved()
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
        `when`(dispatchRepository.findAll()).thenReturn(DISPATCHES)
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock()
        `when`(supplierRepository.findById(any())).thenReturn(SUPPLIER)
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
    }

    private fun givenFindAllDispatches() {
        findAllDispatches = FindAllDispatches(dispatchRepository, supplierRepository, purchaseOrderRepository)
    }

    private fun whenFindingAllDispatches() {
        fullDispatches = findAllDispatches()
    }

    private fun thenDispatchesHaveBeenFound() {
        verify(dispatchRepository).findAll()
    }

    private fun thenSuppliersWhereLookedUp() {
        verify(supplierRepository).findById(any())
    }

    private fun thenPurchaseOrdersWhereRetrieved() {
        verify(purchaseOrderRepository).findByDispatchId(DISPATCHES[0].id)
    }

    private companion object {
        val DISPATCHES = listOf(
                Dispatch(1L, 12L, 100.09, Dispatch.Status.ACCEPTED))

        val SUPPLIER = Supplier(11L, "", "", "", "", "")
    }
}
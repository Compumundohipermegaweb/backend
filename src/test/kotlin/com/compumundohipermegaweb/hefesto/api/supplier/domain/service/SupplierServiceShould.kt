package com.compumundohipermegaweb.hefesto.api.supplier.domain.service

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.SuppliedItem
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class SupplierServiceShould {

    private lateinit var suppliedItemRepository: SuppliedItemRepository
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var supplierService: SupplierService

    private var supplier: Supplier? = null

    @Test
    fun `find a supplier for the given sku`() {
        givenSuppliedItemRepository()
        givenSupplierRepository()
        givenSupplierService()

        whenFindingBySuppliedSku()

        thenSupplierHasBeenFound()
    }

    private fun givenSuppliedItemRepository() {
        suppliedItemRepository = mock()
        `when`(suppliedItemRepository.findBySku(SUPPLIED_ITEM.sku)).thenReturn(SUPPLIED_ITEM)
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock()
    }

    private fun givenSupplierService() {
        supplierService = SupplierService(suppliedItemRepository, supplierRepository)
    }

    private fun whenFindingBySuppliedSku() {
        supplier = supplierService.findBySuppliedSku(SUPPLIED_ITEM.sku)
    }

    private fun thenSupplierHasBeenFound() {
        verify(suppliedItemRepository).findBySku(SUPPLIED_ITEM.sku)
        verify(supplierRepository).findById(SUPPLIED_ITEM.supplierId)
    }

    private companion object {
        val SUPPLIED_ITEM = SuppliedItem(0L, 1L, "1")
    }
}
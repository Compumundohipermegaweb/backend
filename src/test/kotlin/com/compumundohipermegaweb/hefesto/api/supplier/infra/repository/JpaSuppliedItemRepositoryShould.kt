package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.SuppliedItem
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SuppliedItemRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaSuppliedItemRepositoryShould {

    private lateinit var suppliedItemDao: SuppliedItemDao
    private lateinit var suppliedItemRepository: SuppliedItemRepository

    private var suppliedItem: SuppliedItem? = null

    @Test
    fun `find by sku`() {
        givenSuppliedItemDao()
        givenSuppliedItemRepository()

        whenFindingBySku()

        thenSuppliedItemHasBeenFound()
    }

    private fun givenSuppliedItemDao() {
        suppliedItemDao = mock()
        `when`(suppliedItemDao.findBySku(SKU)).thenReturn(SUPPLIED_ITEM_REPRESENTATION)
    }

    private fun givenSuppliedItemRepository() {
        suppliedItemRepository = JpaSuppliedItemRepository(suppliedItemDao)
    }

    private fun whenFindingBySku() {
        suppliedItem = suppliedItemRepository.findBySku(SKU)
    }

    private fun thenSuppliedItemHasBeenFound() {
        verify(suppliedItemDao).findBySku(SKU)
    }

    private companion object {
        const val SKU = "1"
        val SUPPLIED_ITEM_REPRESENTATION = SuppliedItemRepresentation(1L, 1L, SKU)
    }
}
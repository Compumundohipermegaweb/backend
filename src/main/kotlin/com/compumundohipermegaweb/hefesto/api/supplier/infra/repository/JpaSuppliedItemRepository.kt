package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.SuppliedItem
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SuppliedItemRepresentation

class JpaSuppliedItemRepository(private val suppliedItemDao: SuppliedItemDao): SuppliedItemRepository {

    override fun findBySku(sku: String): SuppliedItem? {
        val representation = suppliedItemDao.findBySku(sku)
        return representation?.toSuppliedItem()
    }

    private fun SuppliedItemRepresentation.toSuppliedItem() = SuppliedItem(id, supplierId, sku)
}
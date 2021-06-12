package com.compumundohipermegaweb.hefesto.api.supplier.domain.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.SuppliedItem

interface SuppliedItemRepository {
    fun findBySku(sku: String): SuppliedItem
}

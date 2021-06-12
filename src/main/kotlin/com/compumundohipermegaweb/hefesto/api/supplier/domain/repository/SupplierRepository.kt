package com.compumundohipermegaweb.hefesto.api.supplier.domain.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier

interface SupplierRepository {

    fun save(supplier: Supplier): Supplier
    fun findBySupplySku(sku: String): Supplier?
    fun findById(id: Long): Supplier?
}

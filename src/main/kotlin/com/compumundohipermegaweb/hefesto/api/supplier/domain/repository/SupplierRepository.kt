package com.compumundohipermegaweb.hefesto.api.supplier.domain.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier

interface SupplierRepository {
    fun save(supplier: Supplier): Supplier

}

package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SuppliedItemRepresentation
import org.springframework.data.repository.CrudRepository

interface SuppliedItemDao: CrudRepository<SuppliedItemRepresentation, Long> {
    fun findBySku(sku: String): SuppliedItemRepresentation?
}

package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierRepresentation
import org.springframework.data.repository.CrudRepository

interface SupplierDao : CrudRepository <SupplierRepresentation, Long> {
    fun findBySupplySku(sku: String): SupplierRepresentation?
}
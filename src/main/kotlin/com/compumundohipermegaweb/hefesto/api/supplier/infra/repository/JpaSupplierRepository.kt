package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierDao

class JpaSupplierRepository ( private val springDataSupplierClient : SpringDataSupplierClient) : SupplierRepository {

    override fun save(supplier: Supplier): Supplier{
        val supplierDao = supplier.toRepresentation()
        return springDataSupplierClient.save(supplierDao).toSupplier()
    }

    override fun findBySupplySku(sku: String): Supplier {
        TODO("Not yet implemented")
    }

    private fun Supplier.toRepresentation() = SupplierDao(id, organization, contactName, contactNumber, email, cuit, supplySku)

    private fun SupplierDao.toSupplier() = Supplier (id, organization, contactName, contactNumber, email, cuit, supplySku)

}
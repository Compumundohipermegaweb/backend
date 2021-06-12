package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierRepresentation

class JpaSupplierRepository ( private val supplierDao : SupplierDao) : SupplierRepository {

    override fun save(supplier: Supplier): Supplier{
        val supplierDao = supplier.toRepresentation()
        return this.supplierDao.save(supplierDao).toSupplier()
    }

    override fun findBySupplySku(sku: String): Supplier? {
        val supplierRepresentation = supplierDao.findBySupplySku(sku)
        return supplierRepresentation?.toSupplier()
    }

    private fun Supplier.toRepresentation() = SupplierRepresentation(id, organization, contactName, contactNumber, email, cuit, supplySku)

    private fun SupplierRepresentation.toSupplier() = Supplier (id, organization, contactName, contactNumber, email, cuit, supplySku)

}
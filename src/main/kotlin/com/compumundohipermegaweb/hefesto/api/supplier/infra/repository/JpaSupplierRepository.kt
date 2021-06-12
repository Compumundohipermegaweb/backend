package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierRepresentation

class JpaSupplierRepository ( private val supplierDao : SupplierDao): SupplierRepository {

    override fun save(supplier: Supplier): Supplier{
        val supplierDao = supplier.toRepresentation()
        return this.supplierDao.save(supplierDao).toSupplier()
    }

    override fun findById(id: Long): Supplier? {
        val supplierRepresentation = supplierDao.findById(id)
        return if(supplierRepresentation.isPresent) { supplierRepresentation.get().toSupplier() } else { null }
    }

    private fun Supplier.toRepresentation() = SupplierRepresentation(id, organization, contactName, contactNumber, email, cuit)

    private fun SupplierRepresentation.toSupplier() = Supplier (id, organization, contactName, contactNumber, email, cuit)

}
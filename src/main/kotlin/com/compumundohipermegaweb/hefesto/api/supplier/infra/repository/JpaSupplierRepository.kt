package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierDao

class JpaSupplierRepository ( private val springDataSupplierClient : SpringDataSupplierClient) : SupplierRepository {

    override fun save(supplier: Supplier): Supplier{
        val supplierDao = SupplierDao(supplier.id, supplier.organization, supplier.contactName, supplier.contactNumber, supplier.email ,supplier.cuit )
        return springDataSupplierClient.save(supplierDao).toSupplier()
    }

    private fun SupplierDao.toSupplier() = Supplier (id, organization, contactName, contactNumber, email, cuit)

}
package com.compumundohipermegaweb.hefesto.api.supplier.domain.action

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository

class RegisterSupplier (private val supplierRepository : SupplierRepository){
    operator fun invoke(supplier: Supplier) = supplierRepository.save(supplier)

}

package com.compumundohipermegaweb.hefesto.api.supplier.domain.service

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository

class SupplierService(private val suppliedItemRepository: SuppliedItemRepository,
                      private val supplierRepository: SupplierRepository) {

    fun findBySuppliedSku(sku: String): Supplier? {
        val suppliedItem = suppliedItemRepository.findBySku(sku)
        return supplierRepository.findById(suppliedItem!!.supplierId)
    }
}

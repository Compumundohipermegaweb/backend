package com.compumundohipermegaweb.hefesto.api.supplier

import com.compumundohipermegaweb.hefesto.api.supplier.domain.action.RegisterSupplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test

import org.mockito.Mockito.`when`

class RegisterSupplierShould {
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var registerSupplier: RegisterSupplier
    private lateinit var registeredSupplier: Supplier


    @Test
    fun `register a supplier`(){

        givenSupplierRepository()
        givenRegisterSupplier()
        whenRegisteringTheSupplier()
        thenTheSupplierIsRegisteredSuccessfully()
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock()
    }

    private fun givenRegisterSupplier() {
        registerSupplier = RegisterSupplier(supplierRepository)
    }

    private fun whenRegisteringTheSupplier() {
        registeredSupplier = registerSupplier(SUPPLIER)
    }

    private fun thenTheSupplierIsRegisteredSuccessfully() {
        `when`(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER)
    }

    private companion object {
        val SUPPLIER = Supplier(0L,"","","","","", "")
    }
}
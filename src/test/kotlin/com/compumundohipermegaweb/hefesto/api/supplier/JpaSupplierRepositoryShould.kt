package com.compumundohipermegaweb.hefesto.api.supplier

import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.JpaSupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.SupplierDao
import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierRepresentation
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class JpaSupplierRepositoryShould {
    private lateinit var supplierDao : SupplierDao
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var registeredSupplier: Supplier

    @Test
    fun `save the input`() {
        givenSupplierCrudRepository()
        givenSupplierRepository()

        whenRegisteringSupplier()

        thenSupplierRegistered()

    }

    @Test
    fun `find by supply sku`() {
        givenSupplierCrudRepository()
        givenSupplierRepository()

        supplierRepository.findBySupplySku("1")

        verify(supplierDao).findBySupplySku("1")
    }
    private fun givenSupplierCrudRepository() {
        supplierDao = mock(SupplierDao::class.java)
        `when`(supplierDao.save(SUPPLIER_DAO)).thenReturn(SUPPLIER_DAO)
    }

    private fun givenSupplierRepository() {
        supplierRepository = JpaSupplierRepository(supplierDao)
    }

    private fun thenSupplierRegistered() {
        verify(supplierDao).save(SUPPLIER_DAO)
        then(registeredSupplier).isNotNull
    }

    private fun whenRegisteringSupplier() {
        registeredSupplier = supplierRepository.save(SUPPLIER)
    }

    companion object{
        val SUPPLIER_DAO = SupplierRepresentation(0L,"ORG","A", "111","aaa@aaa","99-99999999-1", "")
        val SUPPLIER = Supplier(0L,"ORG","A", "111","aaa@aaa","99-99999999-1", "")
    }
}
package com.compumundohipermegaweb.hefesto.api.supplier.config

import com.compumundohipermegaweb.hefesto.api.supplier.domain.action.RegisterSupplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.JpaSupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.SupplierDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SupplierConfig {

    @Bean
    fun registerSupplier (supplierRepository: SupplierRepository): RegisterSupplier{
        return RegisterSupplier(supplierRepository)
    }

    @Bean
    fun supplierRepository (supplierCrudRepository: SupplierDao): SupplierRepository {
        return JpaSupplierRepository(supplierCrudRepository)
    }
}
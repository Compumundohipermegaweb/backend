package com.compumundohipermegaweb.hefesto.api.supplier.config

import com.compumundohipermegaweb.hefesto.api.supplier.domain.action.RegisterSupplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.service.SupplierService
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.JpaSuppliedItemRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.JpaSupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.infra.repository.SuppliedItemDao
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
    fun supplierService(suppliedItemRepository: SuppliedItemRepository,
                        supplierRepository: SupplierRepository): SupplierService {
        return SupplierService(suppliedItemRepository, supplierRepository)
    }

    @Bean
    fun supplierRepository (supplierDao: SupplierDao): SupplierRepository {
        return JpaSupplierRepository(supplierDao)
    }

    @Bean
    fun suppliedItemRepository(suppliedItemDao: SuppliedItemDao): SuppliedItemRepository {
        return JpaSuppliedItemRepository(suppliedItemDao)
    }
}
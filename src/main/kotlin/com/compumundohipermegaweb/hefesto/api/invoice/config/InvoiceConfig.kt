package com.compumundohipermegaweb.hefesto.api.invoice.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.JpaInvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.SpringDataInvoiceDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InvoiceConfig {

    @Bean
    fun invoiceService(invoiceRepository: InvoiceRepository, cashStartEndRepository: CashStartEndRepository, cashMovementRepository: CashMovementRepository): DefaultInvoiceService {
        return DefaultInvoiceService(invoiceRepository, cashStartEndRepository, cashMovementRepository)
    }

    @Bean
    fun invoiceRepository(springDataInvoiceDao: SpringDataInvoiceDao): InvoiceRepository {
        return JpaInvoiceRepository(springDataInvoiceDao)
    }
}
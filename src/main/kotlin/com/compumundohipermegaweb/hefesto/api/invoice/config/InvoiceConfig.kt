package com.compumundohipermegaweb.hefesto.api.invoice.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.JpaInvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.SpringDataInvoiceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InvoiceConfig {

    @Bean
    fun invoiceService(invoiceRepository: InvoiceRepository): DefaultInvoiceService {
        return DefaultInvoiceService(invoiceRepository)
    }

    @Bean
    fun invoiceRepository(springDataInvoiceRepository: SpringDataInvoiceRepository): InvoiceRepository {
        return JpaInvoiceRepository(springDataInvoiceRepository)
    }
}
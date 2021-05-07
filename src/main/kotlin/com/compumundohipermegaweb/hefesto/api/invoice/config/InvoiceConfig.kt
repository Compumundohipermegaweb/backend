package com.compumundohipermegaweb.hefesto.api.invoice.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.action.RegisterInvoice
import com.compumundohipermegaweb.hefesto.api.invoice.infrastructure.JpaInvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infrastructure.SpringDataInvoiceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InvoiceConfig {

    @Bean
    fun registerInvoice(jpaInvoiceRepository: JpaInvoiceRepository): RegisterInvoice {
        return RegisterInvoice(jpaInvoiceRepository)
    }

    @Bean
    fun jpaInvoiceRepository(springDataInvoiceRepository: SpringDataInvoiceRepository): JpaInvoiceRepository {
        return JpaInvoiceRepository(springDataInvoiceRepository)
    }
}
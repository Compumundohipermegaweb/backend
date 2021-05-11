package com.compumundohipermegaweb.hefesto.api.sale.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaleConfig {

    @Bean
    fun invoiceSale(saleService: DefaultSaleService, invoiceService: DefaultInvoiceService): InvoiceSale {
        return InvoiceSale(saleService, invoiceService)
    }

    @Bean
    fun saleService(saleRepository: SaleRepository, saleDetailRepository: SaleDetailRepository, salePaymentRepository: SalePaymentRepository): DefaultSaleService {
        return DefaultSaleService(saleRepository, saleDetailRepository, salePaymentRepository)
    }

    @Bean
    fun saleRepository(springDataSaleClient: SpringDataSaleClient): SaleRepository {
        return JpaSaleRepository(springDataSaleClient)
    }

    @Bean
    fun saleDetailRepository(springDataSaleDetailClient: SpringDataSaleDetailClient): SaleDetailRepository {
        return JpaSaleDetailRepository(springDataSaleDetailClient)
    }

    @Bean
    fun salePaymentRepository(springDataSalePaymentClient: SpringDataSalePaymentClient): SalePaymentRepository {
        return JpaSalePaymentRepository(springDataSalePaymentClient)
    }
}
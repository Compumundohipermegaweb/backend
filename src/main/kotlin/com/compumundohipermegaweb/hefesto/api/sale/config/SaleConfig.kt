package com.compumundohipermegaweb.hefesto.api.sale.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailsRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSale
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSaleDetail
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaleConfig {

    @Bean
    fun invoiceSale(saleService: DefaultSaleService, invoiceService: DefaultInvoiceService): InvoiceSale {
        return InvoiceSale(saleService, invoiceService)
    }

    @Bean
    fun saleService(saleRepository: SaleRepository, saleDetailsRepository: SaleDetailsRepository): DefaultSaleService {
        return DefaultSaleService(saleRepository, saleDetailsRepository)
    }

    @Bean
    fun saleRepository(springDataSale: SpringDataSale): SaleRepository {
        return JpaSaleRepository(springDataSale)
    }

    @Bean
    fun saleDetailRepository(springDataSaleDetail: SpringDataSaleDetail): SaleDetailsRepository {
        return JpaSaleDetailRepository(springDataSaleDetail)
    }
}
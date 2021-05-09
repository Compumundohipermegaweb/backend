package com.compumundohipermegaweb.hefesto.api.sale.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentDetailRepository
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
    fun saleService(saleRepository: SaleRepository, saleItemDetailRepository: SaleItemDetailRepository, salePaymentDetailRepository: SalePaymentDetailRepository): DefaultSaleService {
        return DefaultSaleService(saleRepository, saleItemDetailRepository, salePaymentDetailRepository)
    }

    @Bean
    fun saleRepository(springDataSale: SpringDataSale): SaleRepository {
        return JpaSaleRepository(springDataSale)
    }

    @Bean
    fun saleItemDetailRepository(springDataSaleItemDetail: SpringDataSaleItemDetail): SaleItemDetailRepository {
        return JpaSaleItemDetailRepository(springDataSaleItemDetail)
    }

    @Bean
    fun salePaymentDetailRepository(springDataSalePaymentDetail: SpringDataSalePaymentDetail): SalePaymentDetailRepository {
        return JpaSalePaymentDetailRepository(springDataSalePaymentDetail)
    }
}
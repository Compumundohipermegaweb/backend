package com.compumundohipermegaweb.hefesto.api.sale.config

import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.DefaultStockService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaleConfig {

    @Bean
    fun invoiceSale(saleService: DefaultSaleService, invoiceService: DefaultInvoiceService, stockService: DefaultStockService, itemService: DefaultItemService): InvoiceSale {
        return InvoiceSale(saleService, invoiceService, stockService, itemService)
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
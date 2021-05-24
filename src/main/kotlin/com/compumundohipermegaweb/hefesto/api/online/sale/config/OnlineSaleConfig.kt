package com.compumundohipermegaweb.hefesto.api.online.sale.config

import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.online.sale.domain.action.ProcessOnlineSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.RejectedSaleService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OnlineSaleConfig {
    @Bean
    fun processOnlineSale(invoiceSale: InvoiceSale, stockService: StockService, itemService: ItemService, clientService: ClientService, rejectedSaleService: RejectedSaleService): ProcessOnlineSale {
        return ProcessOnlineSale(invoiceSale, stockService, itemService, clientService, rejectedSaleService)
    }
}
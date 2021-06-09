package com.compumundohipermegaweb.hefesto.api.sale.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.GetClientBySaleId
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.PaySale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaleConfig {

    @Bean
    fun invoiceSale(saleService: SaleService,
                    invoiceService: InvoiceService,
                    stockService: StockService,
                    itemService: ItemService,
                    checkingAccountService: CheckingAccountService,
                    paymentMethodService: PaymentMethodService): InvoiceSale {
        return InvoiceSale(saleService, invoiceService, stockService, itemService, checkingAccountService,paymentMethodService)
    }

    @Bean
    fun getClientBySaleId(saleRepository: SaleRepository, clientRepository: ClientRepository): GetClientBySaleId {
        return GetClientBySaleId(saleRepository, clientRepository)
    }

    @Bean
    fun saleService(saleRepository: SaleRepository, saleDetailRepository: SaleDetailRepository, salePaymentRepository: SalePaymentRepository): DefaultSaleService {
        return DefaultSaleService(saleRepository, saleDetailRepository, salePaymentRepository)
    }

    @Bean
    fun paySale(cashMovementRepository: CashMovementRepository, salePaymentRepository: SalePaymentRepository, saleRepository: SaleRepository, checkingAccountRepository: CheckingAccountRepository): PaySale {
        return PaySale(cashMovementRepository, salePaymentRepository, saleRepository, checkingAccountRepository)
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
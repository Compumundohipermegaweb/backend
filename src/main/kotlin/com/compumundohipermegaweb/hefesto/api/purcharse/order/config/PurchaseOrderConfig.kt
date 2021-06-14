package com.compumundohipermegaweb.hefesto.api.purcharse.order.config

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.action.FindAllPurchaseOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.JpaPriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.JpaPurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.PriceToleranceDao
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.PurchaseOrderDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PurchaseOrderConfig {

    @Bean
    fun findAllPurchaseOrders(purchaseOrderRepository: PurchaseOrderRepository): FindAllPurchaseOrders {
        return FindAllPurchaseOrders(purchaseOrderRepository)
    }

    @Bean
    fun purchaseOrderRepository(purchaseOrderDao: PurchaseOrderDao): PurchaseOrderRepository {
        return JpaPurchaseOrderRepository(purchaseOrderDao)
    }

    @Bean
    fun priceToleranceRepository(priceToleranceDao: PriceToleranceDao): PriceToleranceRepository {
        return JpaPriceToleranceRepository(priceToleranceDao)
    }
}
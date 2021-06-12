package com.compumundohipermegaweb.hefesto.api.purcharse.order.config

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.JpaPurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository.PurchaseOrderDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PurchaseOrderConfig {

    @Bean
    fun purchaseOrderRepository(purchaseOrderDao: PurchaseOrderDao): PurchaseOrderRepository {
        return JpaPurchaseOrderRepository(purchaseOrderDao)
    }
}
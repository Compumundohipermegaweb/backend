package com.compumundohipermegaweb.hefesto.api.order.config

import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.JpaOrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.SpringOrderDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderConfig {

    @Bean
    fun orderRepository(springOrderDao: SpringOrderDao): OrderRepository {
        return JpaOrderRepository(springOrderDao)
    }
}
package com.compumundohipermegaweb.hefesto.api.order.config

import com.compumundohipermegaweb.hefesto.api.order.domain.action.GetAllOrders
import com.compumundohipermegaweb.hefesto.api.order.domain.action.UpdateOrderState
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.JpaOrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.SpringOrderDao
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderConfig {

    @Bean
    fun getAllOrders(orderRepository: OrderRepository, saleDetailRepository: SaleDetailRepository): GetAllOrders {
        return GetAllOrders(orderRepository, saleDetailRepository)
    }

    @Bean
    fun updateOrderState(orderRepository: OrderRepository): UpdateOrderState {
        return UpdateOrderState(orderRepository)
    }

    @Bean
    fun orderRepository(springOrderDao: SpringOrderDao): OrderRepository {
        return JpaOrderRepository(springOrderDao)
    }
}
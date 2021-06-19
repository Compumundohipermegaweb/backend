package com.compumundohipermegaweb.hefesto.api.order.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
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
    fun updateOrderState(orderRepository: OrderRepository, cashMovementRepository: CashMovementRepository, cashStartEndRepository: CashStartEndRepository): UpdateOrderState {
        return UpdateOrderState(orderRepository, cashMovementRepository, cashStartEndRepository)
    }

    @Bean
    fun orderRepository(springOrderDao: SpringOrderDao): OrderRepository {
        return JpaOrderRepository(springOrderDao)
    }
}
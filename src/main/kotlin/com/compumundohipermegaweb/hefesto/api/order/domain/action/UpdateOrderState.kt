package com.compumundohipermegaweb.hefesto.api.order.domain.action

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository

class UpdateOrderState(private val orderRepository: OrderRepository) {
    operator fun invoke(orderId: Long): Order? {
        val orderToUpdate = orderRepository.findOrderById(orderId)
        if(orderToUpdate != null) {
            orderToUpdate.state = "ENVIADO"
            return orderRepository.saveOrder(orderToUpdate)
        }
        return null
    }
}
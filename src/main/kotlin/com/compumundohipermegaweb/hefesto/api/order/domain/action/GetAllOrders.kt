package com.compumundohipermegaweb.hefesto.api.order.domain.action

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository

class GetAllOrders(private val orderRepository: OrderRepository) {
    operator fun invoke(branchId: Long): List<Order> {
        return orderRepository.findAllOrdersByBranch(branchId).sortedBy { it.state }.reversed()
    }
}
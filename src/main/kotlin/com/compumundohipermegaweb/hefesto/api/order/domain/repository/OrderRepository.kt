package com.compumundohipermegaweb.hefesto.api.order.domain.repository

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order

interface OrderRepository {
    fun saveOrder(order: Order): Order
    fun findAllOrdersByBranch(branchId: Long): List<Order>
    fun findOrderById(orderId: Long): Order?
}
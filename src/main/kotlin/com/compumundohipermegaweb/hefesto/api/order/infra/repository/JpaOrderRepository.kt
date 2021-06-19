package com.compumundohipermegaweb.hefesto.api.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.representation.OrderRepresentation

class JpaOrderRepository(private val springOrderDao: SpringOrderDao): OrderRepository {

    override fun saveOrder(order: Order): Order {
        return springOrderDao.save(order.toOrderRepresentation()).toOrder()
    }

    override fun findAllOrdersByBranch(branchId: Long): List<Order> {
        return springOrderDao.findAllOrdersByBranch(branchId).map { it.toOrder() }
    }

    private fun OrderRepresentation.toOrder(): Order {
        return Order(id, saleId, branchId, state, shippingPrice, shippingCompany)
    }

    private fun Order.toOrderRepresentation(): OrderRepresentation {
        return OrderRepresentation(id, saleId, branchId, state, shippingPrice, shippingCompany)
    }
}



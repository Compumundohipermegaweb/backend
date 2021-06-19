package com.compumundohipermegaweb.hefesto.api.order.domain.action

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.model.OrderWhitItemDetails
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository

class GetAllOrders(private val orderRepository: OrderRepository,
                    private val saleDetailRepository: SaleDetailRepository) {
    operator fun invoke(branchId: Long): List<OrderWhitItemDetails> {

        val ordersWhitItemDetails = mutableListOf<OrderWhitItemDetails>()
        val order: List<Order> = orderRepository.findAllOrdersByBranch(branchId).sortedBy { it.state }.reversed()

        order.forEach {
            val itemDetails = saleDetailRepository.findBySaleId(it.saleId)
            ordersWhitItemDetails+=OrderWhitItemDetails(it.id, it.saleId, it.branchId, 0L, 0L, it.state, it.shippingPrice, it.shippingCompany, itemDetails)
        }

        return ordersWhitItemDetails
    }
}
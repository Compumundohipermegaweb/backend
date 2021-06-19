package com.compumundohipermegaweb.hefesto.api.order.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.model.OrderWhitItemDetails
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import java.util.*

class UpdateOrderState(private val orderRepository: OrderRepository,
                       private val cashMovementRepository: CashMovementRepository,
                       private val cashStartEndRepository: CashStartEndRepository) {
    operator fun invoke(orderWhitItemDetails: OrderWhitItemDetails): Order? {
        val orderToUpdate = orderRepository.findOrderById(orderWhitItemDetails.id)
        if(orderToUpdate != null) {
            orderToUpdate.state = "ENVIADO"
            orderToUpdate.shippingPrice = orderWhitItemDetails.shippingPrice
            orderToUpdate.shippingCompany = orderWhitItemDetails.shippingCompany
            val cashStartEnd = cashStartEndRepository.findByCashIdAndEndDate(orderWhitItemDetails.cashId)
            cashMovementRepository.save(CashMovement(0L, cashStartEnd.id,"EGRESO", Date(), 7L, "PAGO DE ENVIO DE PEDIDO", 0L, orderToUpdate.id, orderWhitItemDetails.userId, orderWhitItemDetails.shippingPrice, "PAGO A DELIVERY DE PEDIDO"), orderToUpdate.id)
            return orderRepository.saveOrder(orderToUpdate)
        }
        return null
    }
}
package com.compumundohipermegaweb.hefesto.api.order

import com.compumundohipermegaweb.hefesto.api.order.domain.action.UpdateOrderState
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class UpdateOrderStateShould {
    private lateinit var orderRepository: OrderRepository
    private lateinit var updateOrderState: UpdateOrderState

    private var updatedOrder: Order? = null

    @Test
    fun `Get all orders sorted by state`() {
        givenOrderRepository()
        givenSaleUpdateOrderState()

        whenUpdatingAnOrder()

        thenTheOrderHasUpdated()
    }

    private fun givenOrderRepository() {
        orderRepository = mock()
        `when`(orderRepository.findOrderById(0L)).thenReturn(ORDER)
        `when`(orderRepository.saveOrder(UPDATED_ORDER)).thenReturn(UPDATED_ORDER)
    }

    private fun givenSaleUpdateOrderState() {
        updateOrderState = UpdateOrderState(orderRepository)
    }

    private fun whenUpdatingAnOrder() {
        updatedOrder = updateOrderState.invoke(0L)
    }

    private fun thenTheOrderHasUpdated() {
        verify(orderRepository).findOrderById(0L)
        then(updatedOrder).isEqualTo(UPDATED_ORDER)
    }

    private companion object {
        private val ORDER = Order(0L, 1L, 0L,"PENDIENTE", 0.0, "")
        private val UPDATED_ORDER = Order(0L,  1L, 0L,"ENVIADO", 0.0, "")
    }
}
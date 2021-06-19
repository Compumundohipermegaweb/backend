package com.compumundohipermegaweb.hefesto.api.order

import com.compumundohipermegaweb.hefesto.api.order.domain.action.GetAllOrders
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllOrdersShould {

    private lateinit var orderRepository: OrderRepository
    private lateinit var getAllOrders: GetAllOrders

    private lateinit var allOrderSortByState: List<Order>

    @Test
    fun `Get all orders sorted by state`() {
        givenOrderRepository()
        givenGetAllOrders()

        whenGettingAllOrdes()

        thenTheOrderHasRetunrSortedByState()
    }

    private fun givenOrderRepository() {
        orderRepository = mock()
        `when`(orderRepository.findAllOrdersByBranch(0L)).thenReturn(listOf(ANOTHER_SHIPPED_ORDER, SHIPPED_ORDER, PENDING_ORDER, ANOTHER_PENDING_ORDER))

    }

    private fun givenGetAllOrders() {
        getAllOrders = GetAllOrders(orderRepository)
    }

    private fun whenGettingAllOrdes() {
        allOrderSortByState = getAllOrders.invoke(0L)
    }

    private fun thenTheOrderHasRetunrSortedByState() {
        verify(orderRepository).findAllOrdersByBranch(0L)
        then(allOrderSortByState[0]).isEqualTo(ANOTHER_PENDING_ORDER)
        then(allOrderSortByState[1]).isEqualTo(PENDING_ORDER)
        then(allOrderSortByState[2]).isEqualTo(SHIPPED_ORDER)
        then(allOrderSortByState[3]).isEqualTo(ANOTHER_SHIPPED_ORDER)
    }

    private companion object {
        private val PENDING_ORDER = Order(1L, 1L, 0L, "PENDIENTE", 0.0, "")
        private val ANOTHER_PENDING_ORDER = Order(2L, 2L, 0L, "PENDIENTE", 0.0, "")
        private val SHIPPED_ORDER = Order(1L, 1L, 0L, "ENTREGADO", 150.0, "RAPPI")
        private val ANOTHER_SHIPPED_ORDER = Order(1L, 1L, 0L, "ENTREGADO", 150.0, "RAPPI")
    }
}
package com.compumundohipermegaweb.hefesto.api.order

import com.compumundohipermegaweb.hefesto.api.order.domain.action.GetAllOrders
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.model.OrderWhitItemDetails
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllOrdersShould {

    private lateinit var orderRepository: OrderRepository
    private lateinit var saleDetailRepository: SaleDetailRepository
    private lateinit var getAllOrders: GetAllOrders

    private lateinit var allOrderSortByState: List<OrderWhitItemDetails>

    @Test
    fun `Get all orders sorted by state`() {
        givenOrderRepository()
        givenSaleDetailRepository()
        givenGetAllOrders()

        whenGettingAllOrdes()

        thenTheOrderHasRetunrSortedByState()
    }

    private fun givenOrderRepository() {
        orderRepository = mock()
        `when`(orderRepository.findAllOrdersByBranch(0L)).thenReturn(listOf(ANOTHER_SHIPPED_ORDER, SHIPPED_ORDER, PENDING_ORDER, ANOTHER_PENDING_ORDER))
    }

    private fun givenSaleDetailRepository() {
        saleDetailRepository = mock()
        `when`(saleDetailRepository.findBySaleId(1L)).thenReturn(listOf(SALE_DETAIL))
        `when`(saleDetailRepository.findBySaleId(2L)).thenReturn(listOf(SALE_DETAIL))
        `when`(saleDetailRepository.findBySaleId(3L)).thenReturn(listOf(SALE_DETAIL))
        `when`(saleDetailRepository.findBySaleId(4L)).thenReturn(listOf(SALE_DETAIL))

    }

    private fun givenGetAllOrders() {
        getAllOrders = GetAllOrders(orderRepository, saleDetailRepository)
    }

    private fun whenGettingAllOrdes() {
        allOrderSortByState = getAllOrders.invoke(0L)
    }

    private fun thenTheOrderHasRetunrSortedByState() {
        verify(orderRepository).findAllOrdersByBranch(0L)
        then(allOrderSortByState[0]).isEqualTo(ANOTHER_PENDING_ORDER_WHIT_DETAILS)
        then(allOrderSortByState[1]).isEqualTo(PENDING_ORDER_WHIT_DETAILS)
        then(allOrderSortByState[2]).isEqualTo(SHIPPED_ORDER_WHIT_DETAILS)
        then(allOrderSortByState[3]).isEqualTo(ANOTHER_SHIPPED_ORDER_WHIT_DETAILS)
    }

    private companion object {
        private val SALE_DETAIL = SaleDetail(0L, "", "", 0, 0.0)

        private val PENDING_ORDER = Order(1L, 1L, 0L,"PENDIENTE", 0.0, "")
        private val ANOTHER_PENDING_ORDER = Order(2L,  2L, 0L,"PENDIENTE", 0.0, "")
        private val SHIPPED_ORDER = Order(1L, 3L, 0L,"ENTREGADO", 150.0, "RAPPI")
        private val ANOTHER_SHIPPED_ORDER = Order(1L, 4L, 0L,"ENTREGADO", 150.0, "RAPPI")

        private val PENDING_ORDER_WHIT_DETAILS = OrderWhitItemDetails(1L, 1L,  0L, 0L, 0L,"PENDIENTE", 0.0, "", listOf(SALE_DETAIL))
        private val ANOTHER_PENDING_ORDER_WHIT_DETAILS = OrderWhitItemDetails(2L,  2L, 0L, 0L, 0L,"PENDIENTE", 0.0, "", listOf(SALE_DETAIL))
        private val SHIPPED_ORDER_WHIT_DETAILS = OrderWhitItemDetails(1L, 3L,0L, 0L, 0L, "ENTREGADO", 150.0, "RAPPI", listOf(SALE_DETAIL))
        private val ANOTHER_SHIPPED_ORDER_WHIT_DETAILS = OrderWhitItemDetails(1L, 4L, 0L, 0L, 0L,"ENTREGADO", 150.0, "RAPPI", listOf(SALE_DETAIL))
    }
}
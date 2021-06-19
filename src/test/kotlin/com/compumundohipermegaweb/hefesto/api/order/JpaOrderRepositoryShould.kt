package com.compumundohipermegaweb.hefesto.api.order

import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.JpaOrderRepository
import com.compumundohipermegaweb.hefesto.api.order.infra.repository.SpringOrderDao
import com.compumundohipermegaweb.hefesto.api.order.infra.representation.OrderRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaOrderRepositoryShould {

    private lateinit var springOrderDao: SpringOrderDao
    private lateinit var orderRepository: OrderRepository

    private lateinit var savedOrder: Order
    private lateinit var ordersFound: List<Order>
    private var orderFound: Order? = null

    @Test
    fun `save the order`() {
        givenSpringOrderDao()
        givenOrderRepository()
        
        whenSavingTheOrder()
        
        thenTheOrderHasSuccessfullySave()
    }

    @Test
    fun `find all orders`() {
        givenSpringOrderDao()
        givenOrderRepository()

        whenFindingTheOrders()

        thenTheOrdersHasSuccessfullyFound()
    }

    @Test
    fun `find order by id`() {
        givenSpringOrderDao()
        givenOrderRepository()

        whenFindingTheOrderById()

        thenTheOrderHasSuccessfullyFound()
    }

    private fun givenSpringOrderDao() {
        springOrderDao = mock()
        `when`(springOrderDao.save(ORDER_REPRESENTATION)).thenReturn(ORDER_REPRESENTATION)
        `when`(springOrderDao.findAllOrdersByBranch(0L)).thenReturn(listOf(ORDER_REPRESENTATION, ANOTHER_ORDER_REPRESENTATION))
        `when`(springOrderDao.findById(0L)).thenReturn(Optional.of(ORDER_REPRESENTATION))

    }
    
    private fun givenOrderRepository() {
        orderRepository = JpaOrderRepository(springOrderDao)
    }
    
    private fun whenSavingTheOrder() {
        savedOrder = orderRepository.saveOrder(ORDER)
    }

    private fun whenFindingTheOrders() {
        ordersFound = orderRepository.findAllOrdersByBranch(0L)
    }

    private fun whenFindingTheOrderById() {
        orderFound = orderRepository.findOrderById(0L)
    }
    
    private fun thenTheOrderHasSuccessfullySave() {
        verify(springOrderDao).save(ORDER_REPRESENTATION)
        then(savedOrder).isEqualTo(ORDER)
    }

    private fun thenTheOrdersHasSuccessfullyFound() {
        verify(springOrderDao).findAllOrdersByBranch(0L)
        then(ordersFound).isEqualTo(listOf(ORDER, ANOTHER_ORDER))
    }

    private fun thenTheOrderHasSuccessfullyFound() {
        verify(springOrderDao).findById(0L)
        then(orderFound).isEqualTo(ORDER)
    }

    private companion object {
        private val ORDER_REPRESENTATION = OrderRepresentation(0L, 0L, 0L, "", 0.0, "")
        private val ORDER = Order(0L, 0L, 0L, "", 0.0, "")

        private val ANOTHER_ORDER_REPRESENTATION = OrderRepresentation(0L, 0L, 0L, "", 0.0, "")
        private val ANOTHER_ORDER = Order(0L, 0L, 0L, "", 0.0, "")
    }

}
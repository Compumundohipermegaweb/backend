package com.compumundohipermegaweb.hefesto.api.order

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.order.domain.action.UpdateOrderState
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.domain.model.OrderWhitItemDetails
import com.compumundohipermegaweb.hefesto.api.order.domain.repository.OrderRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class UpdateOrderStateShould {
    private lateinit var orderRepository: OrderRepository
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var cashStartEndRepository: CashStartEndRepository
    private lateinit var updateOrderState: UpdateOrderState

    private var updatedOrder: Order? = null

    @Test
    fun `Get all orders sorted by state`() {
        givenOrderRepository()
        givenCashMovementRepository()
        givenCashStartEndRepository()
        givenSaleUpdateOrderState()

        whenUpdatingAnOrder()

        thenTheOrderHasUpdated()
    }

    private fun givenOrderRepository() {
        orderRepository = mock()
        `when`(orderRepository.findOrderById(0L)).thenReturn(ORDER)
        `when`(orderRepository.saveOrder(UPDATED_ORDER)).thenReturn(UPDATED_ORDER)
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.save(CASH_MOVEMENT, 0L)).thenReturn(CASH_MOVEMENT)
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.findByCashIdAndEndDate(0L)).thenReturn(CASH_START_END)
    }

    private fun givenSaleUpdateOrderState() {
        updateOrderState = UpdateOrderState(orderRepository, cashMovementRepository, cashStartEndRepository)
    }

    private fun whenUpdatingAnOrder() {
        updatedOrder = updateOrderState.invoke(ORDER_WHIT_ITEM_DETAILS)
    }

    private fun thenTheOrderHasUpdated() {
        verify(orderRepository).findOrderById(0L)
        then(updatedOrder).isEqualTo(UPDATED_ORDER)
    }

    private companion object {
        private val DATE = Date()
        private val SALE_DETAIL = SaleDetail(0L, "", "", 0, 0.0)
        private val ORDER_WHIT_ITEM_DETAILS = OrderWhitItemDetails(0L, 1L, 0L, 0L, 0L, "PENDIENTE", 150.0, "RAPPI", listOf(SALE_DETAIL))
        private val ORDER = Order(0L, 1L, 0L,"PENDIENTE", 0.0, "RAPPI")
        private val UPDATED_ORDER = Order(0L,  1L, 0L,"ENVIADO", 150.0, "RAPPI")
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
        private val CASH_START_END = CashStartEnd(0L, 0L, DATE, 0.0, 0L, null, 0.0, 0.0, DATE)
    }
}
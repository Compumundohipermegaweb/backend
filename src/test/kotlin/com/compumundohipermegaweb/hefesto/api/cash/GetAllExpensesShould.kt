package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetAllExpenses
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Expense
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class GetAllExpensesShould {
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var getAllExpenses: GetAllExpenses

    private lateinit var expens: List<Expense>

    @Test
    fun `get all incomes`() {
        givenCashMovementRepository()
        givenPaymentMethodRepository()
        givenGetAllExpenses()

        whenGettingAllExpenses()

        thenAllIncomesAreGet()
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.findByCashStartEndId(0L)) .thenReturn(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT))
    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = mock()
        `when`(paymentMethodRepository.findById(0L)).thenReturn(PAYMENT_METHOD)

    }

    private fun givenGetAllExpenses() {
        getAllExpenses = GetAllExpenses(cashMovementRepository, paymentMethodRepository)
    }

    private fun whenGettingAllExpenses() {
        expens = getAllExpenses.invoke(0L)
    }

    private fun thenAllIncomesAreGet() {
        verify(cashMovementRepository).findByCashStartEndId(0L)
        verify(paymentMethodRepository, times(2)).findById(0L)
        then(expens).isEqualTo(listOf(TRANSACTION, ANOTHER_TRANSACTION))
    }

    private companion object {
        private val DATE = Date()
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "EGRESO", DATE, 0L, "FLETE", 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "EGRESO", DATE, 0L, "COMPRA", 0L, 0L,0L, 0.0, "")
        private val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVE")
        private val TRANSACTION = Expense(0L, DATE, "FLETE", "", "EFECTIVO", 0.0)
        private val ANOTHER_TRANSACTION = Expense(1L, DATE, "COMPRA", "", "EFECTIVO", 0.0)
    }
}
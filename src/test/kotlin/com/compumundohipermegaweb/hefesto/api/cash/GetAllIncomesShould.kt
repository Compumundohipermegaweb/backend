package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetAllIncomes
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Income
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class GetAllIncomesShould {
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var saleRepository: SaleRepository
    private lateinit var salePaymentRepository: SalePaymentRepository
    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var getAllIncomes: GetAllIncomes

    private lateinit var incomes: List<Income>

    @Test
    fun `get all incomes`() {
        givenCashMovementRepository()
        givenSaleRepository()
        givenSalePaymentRepository()
        givenPaymentMethodRepository()
        givenGetAllIncomes()

        whenGettingAllIncomes()

        thenAllIncomesAreGet()
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.findByCashStartEndId(0L)).thenReturn(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT))
    }

    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.findById(0L)).thenReturn(SALE_DAO)

    }

    private fun givenSalePaymentRepository() {
        salePaymentRepository = mock()
        `when`(salePaymentRepository.findBySaleId(0L)).thenReturn(listOf(SALE_PAYMENT_DETAIL))
    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = mock()
        `when`(paymentMethodRepository.findById(0L)).thenReturn(PAYMENT_METHOD)

    }

    private fun givenGetAllIncomes() {
        getAllIncomes = GetAllIncomes(cashMovementRepository, saleRepository, salePaymentRepository, paymentMethodRepository)
    }

    private fun whenGettingAllIncomes() {
        incomes = getAllIncomes.invoke(0L)
    }

    private fun thenAllIncomesAreGet() {
        verify(cashMovementRepository).findByCashStartEndId(0L)
        verify(saleRepository, times(2)).findById(0L)
        verify(salePaymentRepository, times(2)).findBySaleId(0L)
        then(incomes).isEqualTo(listOf(TRANSACTION, ANOTHER_TRANSACTION))
    }

    private companion object {
        private val DATE = Date()
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, 0L, 0.0, "")
        private val SALE_PAYMENT_DETAIL = SalePayment(0L, "", 0.0)
        private val TRANSACTION = Income(0L, DATE, "VENTA", "", "", 0.0)
        private val ANOTHER_TRANSACTION = Income(1L, DATE, "VENTA", "", "", 0.0)
        private val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVE")
    }
}
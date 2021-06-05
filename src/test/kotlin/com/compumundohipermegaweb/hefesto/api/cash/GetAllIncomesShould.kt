package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetAllIncomes
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Income
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
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
    private lateinit var clientRepository: ClientRepository
    private lateinit var getAllIncomes: GetAllIncomes

    private lateinit var incomes: List<Income>

    @Test
    fun `get all incomes`() {
        givenCashMovementRepository()
        givenSaleRepository()
        givenSalePaymentRepository()
        givenClientRepository()
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

    private fun givenClientRepository() {
        clientRepository = mock()
        `when`(clientRepository.findById(0L)).thenReturn(CLIENT)
    }

    private fun givenGetAllIncomes() {
        getAllIncomes = GetAllIncomes(cashMovementRepository, saleRepository, salePaymentRepository, clientRepository)
    }

    private fun whenGettingAllIncomes() {
        incomes = getAllIncomes.invoke(0L)
    }

    private fun thenAllIncomesAreGet() {
        verify(cashMovementRepository).findByCashStartEndId(0L)
        verify(saleRepository, times(2)).findById(0L)
        verify(salePaymentRepository, times(2)).findBySaleId(0L)
        verify(clientRepository, times(2)).findById(0L)
        then(incomes.size).isEqualTo(2)
        then(incomes[0]).isNotNull
        then(incomes[1]).isNotNull
        then(incomes).isEqualTo(listOf(TRANSACTION, ANOTHER_TRANSACTION))
    }

    private companion object {
        private val DATE = Date()
        private val CLIENT = Client(0L, "00000000","Name", "Sar", "", 0.0, "", "", "")
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, 0L, 0.0, "")
        private val SALE_PAYMENT_DETAIL = SalePayment(0L, "", 0.0)
        private val LIST_OF_PAYMENTS = listOf(SALE_PAYMENT_DETAIL)
        private val TRANSACTION = Income(0L, DATE, 0L, "VENTA", "", LIST_OF_PAYMENTS, 0.0, 0L, CLIENT,0L)
        private val ANOTHER_TRANSACTION = Income(1L, DATE, 0L, "VENTA", "", LIST_OF_PAYMENTS, 0.0, 0L, CLIENT,0L)
    }
}
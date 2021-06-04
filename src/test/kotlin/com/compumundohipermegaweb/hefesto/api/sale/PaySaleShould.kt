package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.PaySale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaySaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class PaySaleShould {

    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var salePaymentRepository: SalePaymentRepository

    private lateinit var paySale: PaySale

    @Test
    fun `pay sale`() {
        givenCashMovementRepository()
        givenSalePaymentRepository()
        givenPaySale()

        whenPayingTheSale()

        thenTheSaleIsPaid()
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.save(CASH_MOVEMENT, CASH_MOVEMENT.cashStartEndId)).thenReturn(CASH_MOVEMENT)
    }

    private fun givenSalePaymentRepository() {
        salePaymentRepository = mock()
        `when`(salePaymentRepository.save(SALE_PAYMENT, CASH_MOVEMENT.transactionId)).thenReturn(SALE_PAYMENT)
    }

    private fun givenPaySale() {
        paySale = PaySale(cashMovementRepository, salePaymentRepository)
    }

    private fun whenPayingTheSale() {
        paySale.invoke(PAY_SALE_REQUEST)
    }

    private fun thenTheSaleIsPaid() {
        verify(salePaymentRepository).save(SALE_PAYMENT, CASH_MOVEMENT.transactionId)
        verify(cashMovementRepository).save(CASH_MOVEMENT, CASH_MOVEMENT.transactionId)
    }

    private companion object {
        private val DATE = Date()
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val PAYMENT_REQUEST = PaymentRequest("EFECTIVO",  200.50)
        private val PAY_SALE_REQUEST = PaySaleRequest(CASH_MOVEMENT, listOf(PAYMENT_REQUEST))
        private val SALE_PAYMENT = SalePayment(0L, "EFECTIVO" ,200.50)
    }
}
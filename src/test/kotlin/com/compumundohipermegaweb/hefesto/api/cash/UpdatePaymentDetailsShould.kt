package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.UpdatePaymentDetails
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class UpdatePaymentDetailsShould {
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var salePaymentRepository: SalePaymentRepository

    private lateinit var updatePaymentDetails: UpdatePaymentDetails

    @Test
    fun `register a cash`() {
        givenCashMovementRepository()
        givenSalePaymentRepository()
        givenUpdatePaymentDetails()

        whenUpdatingSalePayments()

        thenTheSalePaymentsAreSuccessfullyUpdated()
    }


    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.findById(0L)).thenReturn(CASH_MOVEMENT)
    }

    private fun givenSalePaymentRepository() {
        salePaymentRepository = mock()
        `when`(salePaymentRepository.findBySaleId(0L)).thenReturn(SALE_PAYMENT_DETAIL)
    }

    private fun givenUpdatePaymentDetails() {
        updatePaymentDetails = UpdatePaymentDetails(cashMovementRepository, salePaymentRepository)
    }

    private fun whenUpdatingSalePayments() {
        updatePaymentDetails.invoke(0L, listOf(PAYMENT_REQUEST))
    }

    private fun thenTheSalePaymentsAreSuccessfullyUpdated() {
        verify(cashMovementRepository).findById(0L)
        verify(salePaymentRepository).findBySaleId(0L)
    }

    private companion object {
        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L, "EFECTIVO",200.50))
        val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", Date(), 0L, "VENTA", 0L, 0L,0L, 0.0, "VENTA LOCAL")
        val PAYMENT_REQUEST = PaymentRequest("EFECTIVO",  200.50)
    }
}
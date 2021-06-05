package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.PaySale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaySaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class PaySaleShould {

    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var salePaymentRepository: SalePaymentRepository
    private lateinit var saleRepository: SaleRepository
    private lateinit var checkingAccountRepository: CheckingAccountRepository

    private lateinit var paySale: PaySale

    @Test
    fun `pay sale`() {
        givenCashMovementRepository()
        givenSalePaymentRepository()
        givenSaleRepository()
        givenCheckingAccountRepository()
        givenPaySale()

        whenPayingTheSale()

        thenTheSaleIsPaid()
    }

    @Test
    fun `pay sale whit checking account`() {
        givenCashMovementRepository()
        givenSalePaymentRepository()
        givenSaleRepository()
        givenCheckingAccountRepository()
        givenPaySale()

        whenPayingTheSaleWhitCheckingAccount()

        thenTheSaleIsPaidWhitCheckingAccount()
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.save(CASH_MOVEMENT, CASH_MOVEMENT.cashStartEndId)).thenReturn(CASH_MOVEMENT)
        `when`(cashMovementRepository.save(CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT, CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT.cashStartEndId)).thenReturn(
            CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT)
    }

    private fun givenSalePaymentRepository() {
        salePaymentRepository = mock()
        `when`(salePaymentRepository.save(SALE_PAYMENT, 0L)).thenReturn(SALE_PAYMENT)
        `when`(salePaymentRepository.save(SALE_PAYMENT_WHIT_CHECKING_ACCOUNT, 0L)).thenReturn(SALE_PAYMENT_WHIT_CHECKING_ACCOUNT)
    }

    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.findById(0L)).thenReturn(SALE_DAO)
    }

    private fun givenCheckingAccountRepository() {
        checkingAccountRepository = mock()
        `when`(checkingAccountRepository.findCheckingAccountByClientId(0L)).thenReturn(CHECKING_ACCOUNT)
        `when`(checkingAccountRepository.updateBalance(any(), any())).then {  }
        `when`(checkingAccountRepository.updateBalanceDue(any(), any())).then {  }
    }

    private fun givenPaySale() {
        paySale = PaySale(cashMovementRepository, salePaymentRepository, saleRepository, checkingAccountRepository)
    }

    private fun whenPayingTheSale() {
        paySale.invoke(PAY_SALE_REQUEST)
    }

    private fun whenPayingTheSaleWhitCheckingAccount() {
        paySale.invoke(PAY_SALE_REQUEST_WHIT_CHECKING_ACCOUNT)
    }

    private fun thenTheSaleIsPaid() {
        verify(salePaymentRepository).save(SALE_PAYMENT, CASH_MOVEMENT.sourceId)
        verify(cashMovementRepository).save(CASH_MOVEMENT, CASH_MOVEMENT.sourceId)
    }

    private fun thenTheSaleIsPaidWhitCheckingAccount() {
        verify(salePaymentRepository).save(SALE_PAYMENT_WHIT_CHECKING_ACCOUNT, CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT.sourceId)
        verify(cashMovementRepository).save(CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT, CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT.sourceId)
        verify(checkingAccountRepository).findCheckingAccountByClientId(0L)
        verify(checkingAccountRepository).updateBalance(any(), any())
        verify(checkingAccountRepository).updateBalanceDue(any(), any())
        verify(saleRepository).findById(0L)
    }

    private companion object {
        private val DATE = Date()

        private val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val PAYMENT_REQUEST = PaymentRequest("EFECTIVO",  200.50)
        private val PAY_SALE_REQUEST = PaySaleRequest(CASH_MOVEMENT, listOf(PAYMENT_REQUEST))
        private val SALE_PAYMENT = SalePayment(0L, "EFECTIVO" ,200.50)

        private val CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L,0L, 0.0, "")
        private val PAYMENT_REQUEST_WHIT_CHECKING_ACCOUNT = PaymentRequest("CUENTA CORRIENTE",  200.50)
        private val PAY_SALE_REQUEST_WHIT_CHECKING_ACCOUNT = PaySaleRequest(CASH_MOVEMENT_WHIT_CHECKING_ACCOUNT, listOf(PAYMENT_REQUEST_WHIT_CHECKING_ACCOUNT))
        private val SALE_PAYMENT_WHIT_CHECKING_ACCOUNT = SalePayment(0L, "CUENTA CORRIENTE" ,200.50)
        private val CHECKING_ACCOUNT = CheckingAccount(0L, 0L, 0.0, 0.0, 0.0)


        private val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, 0L, 0.0, "")
    }
}
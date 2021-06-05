package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.OpenCash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class OpenCashShould {
    private lateinit var cashRepository: CashRepository
    private lateinit var cashStartEndRepository: CashStartEndRepository
    private lateinit var cashMovementRepository: CashMovementRepository

    private lateinit var openCash: OpenCash

    private var openedCash: Cash? = null

    @Test
    fun `open a cash`() {
        givenCashRepository()
        givenCashStartEndRepository()
        givenCashMovementRepository()
        givenOpenCash()

        whenOpeningACash()

        thenTheCashIsSuccessfullyOpen()
    }

    @Test
    fun `not open a cash already open`() {
        givenCashRepository()
        givenCashStartEndRepository()
        givenCashMovementRepository()
        givenOpenCash()

        whenOpeningACashAlreadyOpen()

        thenTheCashAlreadyOpenIsNotOpenAgain()
    }

    @Test
    fun `return null if try open a nonexist cash`() {
        givenCashRepository()
        givenCashStartEndRepository()
        givenCashMovementRepository()
        givenOpenCash()

        whenOpeningNonExistsCash()

        thenReturnNull()
    }

    private fun givenCashRepository() {
        cashRepository = mock()
        `when`(cashRepository.findById(0L)).thenReturn(CASH_TO_OPEN)
        `when`(cashRepository.findById(1L)).thenReturn(ALREADY_OPENED_CASH)
        `when`(cashRepository.findById(2L)).thenReturn(null)
        `when`(cashRepository.save(CASH_TO_OPEN)).thenReturn(CASH_TO_OPEN)
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.save(CASH_MOVEMENT, 0L)).thenReturn(CASH_MOVEMENT)
    }


    private fun givenCashStartEndRepository() {
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.save(any())).thenReturn(CASH_START_END)
    }

    private fun givenOpenCash() {
        openCash = OpenCash(cashRepository, cashStartEndRepository, cashMovementRepository)
    }

    private fun whenOpeningACash() {
        openedCash = openCash.invoke(OPEN_REQUEST)
    }

    private fun whenOpeningACashAlreadyOpen() {
        openedCash = openCash.invoke(ALREADY_OPEN_REQUEST)
    }

    private fun whenOpeningNonExistsCash() {
        openedCash = openCash.invoke(NONEXISTENT_OPEN_REQUEST)
    }

    private fun thenTheCashIsSuccessfullyOpen() {
        verify(cashRepository).findById(0L)
        verify(cashRepository).save(CASH_TO_OPEN)
        verify(cashStartEndRepository).save(any())
        verify(cashMovementRepository).save(any(), any())
        then(openedCash).isEqualTo(OPENED_CASH)
    }

    private fun thenTheCashAlreadyOpenIsNotOpenAgain() {
        then(openedCash).isEqualTo(ALREADY_OPENED_CASH)
    }

    private fun thenReturnNull() {
        then(openedCash).isNull()
    }

    private companion object {
        private val DATE = Date()
        private val OPEN_REQUEST = OpenRequest(0L, 0L, 0.0)
        private val ALREADY_OPEN_REQUEST = OpenRequest(1L, 1L, 0.0)
        private val NONEXISTENT_OPEN_REQUEST = OpenRequest(2L, 2L, 0.0)
        private val CASH_TO_OPEN =  Cash(0L, 0L, 0L, "CLOSE")
        private val OPENED_CASH =  Cash(0L, 0L, 0L, "OPEN")
        private val ALREADY_OPENED_CASH =  Cash(1L, 1L, 1L, "OPEN")
        private val CASH_START_END = CashStartEnd(0L, 0L, DATE, 0.0, 0L, null, 0.0, 0.0, DATE)
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "APERTURA", 0L, 0L, 0L, 0.0, "APERTURA DE CAJA")
    }
}
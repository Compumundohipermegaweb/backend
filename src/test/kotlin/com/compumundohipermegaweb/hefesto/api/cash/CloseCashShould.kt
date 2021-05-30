package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.CloseCash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CloseRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class CloseCashShould {
    private lateinit var cashRepository: CashRepository
    private lateinit var cashStartEndRepository: CashStartEndRepository

    private lateinit var closeCash: CloseCash

    private var closedCash: Cash? = null

    @Test
    fun `close a cash`() {
        givenCashRepository()
        givenCashStartEndRepository()
        givenCloseCash()

        whenClosingACash()

        thenTheCashIsSuccessfullyClosed()
    }

    private fun givenCashRepository() {
        cashRepository = mock()
        `when`(cashRepository.findById(0L)).thenReturn(CASH_TO_CLOSE)
        `when`(cashRepository.save(CASH_TO_CLOSE)).thenReturn(CASH_TO_CLOSE)
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.findByCashIdAndEndDate(0L)).thenReturn(CASH_START_END)
        `when`(cashStartEndRepository.save(any())).thenReturn(CASH_START_END_CLOSED)
    }

    private fun givenCloseCash() {
        closeCash = CloseCash(cashRepository, cashStartEndRepository)
    }

    private fun whenClosingACash() {
        closedCash = closeCash.invoke(CLOSE_REQUEST)
    }

    private fun thenTheCashIsSuccessfullyClosed() {
        verify(cashRepository).findById(0L)
        verify(cashRepository).save(CASH_TO_CLOSE)
        verify(cashStartEndRepository).findByCashIdAndEndDate(0L)
        verify(cashStartEndRepository).save(CASH_START_END)
        then(closedCash).isEqualTo(CLOSED_CASH)
    }

    private companion object {
        private val CLOSE_REQUEST = CloseRequest(0L, 0L, 0.0, 0.0)
        private val CASH_TO_CLOSE =  Cash(0L, 0L, 0L, "OPEN")
        private val CLOSED_CASH =  Cash(0L, 0L, 0L, "CLOSE")
        private val CASH_START_END = CashStartEnd(0L, 0L, Date(), 0.0, 0L, null, 0.0, 0.0, Date())
        private val DATE = Date()
        private val CASH_START_END_CLOSED = CashStartEnd(0L, 0L, Date(), 0.0, 0L, DATE, 0.0, 0.0, Date())

    }
}
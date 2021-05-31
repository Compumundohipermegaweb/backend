package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetCashByUserId
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class GetCashByUserIdShould {

    private lateinit var cashStartEndRepository: CashStartEndRepository
    private lateinit var getCashByUserId: GetCashByUserId

    private var openCashStartEndId: Long = 0

    @Test
    fun `find a cash start end by user id`() {
        givenCashStartEndRepository()
        givenGetCashByUserId()

        whenFindingACashByUserId()

        thenTheCashStartByUserIdIsFound()
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.findByUserId(0L)).thenReturn(listOf(CASH_START_END, CASH_START_NOT_CLOSED))
    }

    private fun givenGetCashByUserId() {
        getCashByUserId = GetCashByUserId(cashStartEndRepository)
    }

    private fun whenFindingACashByUserId() {
        openCashStartEndId = getCashByUserId.invoke(0L)
    }

    private fun thenTheCashStartByUserIdIsFound() {
        verify(cashStartEndRepository).findByUserId(0L)
        then(openCashStartEndId).isEqualTo(0L)
    }

    private companion object {
        private val CASH_START_END = CashStartEnd(0L, 0L, Date(), 0.0, 0L,null,0.0,0.0, Date())
        private val CASH_START_NOT_CLOSED = CashStartEnd(1L, 0L, Date(), 0.0, 0L, Date(),0.0,0.0, Date())

    }
}
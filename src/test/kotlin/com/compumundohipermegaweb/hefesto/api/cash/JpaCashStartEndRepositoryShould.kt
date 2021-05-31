package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.JpaCashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.SpringCashStartEndDao
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashStartEndRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaCashStartEndRepositoryShould {
    private lateinit var springCashStartEndDao: SpringCashStartEndDao
    private lateinit var cashStartEndRepository: CashStartEndRepository

    private lateinit var savedCashStartEnd: CashStartEnd
    private lateinit var cashStartEndNotClosedFound: CashStartEnd
    private lateinit var cashStartEndNotClosedFoundByUser: List<CashStartEnd>

    @Test
    fun `save a cash start-end`() {
        givenSpringCashStartEndDao()
        givenCashStartEndRepository()

        whenSavingCashStartEnd()

        thenTheCashStartEndIsSuccessfullySaved()
    }

    @Test
    fun `find a cash start end opened but not closed`() {
        givenSpringCashStartEndDao()
        givenCashStartEndRepository()

        whenFindingACashStarNotClosed()

        thenTheCashStartNotCloseIsSuccessfullyFound()
    }

    @Test
    fun `find a cash start by user id`() {
        givenSpringCashStartEndDao()
        givenCashStartEndRepository()

        whenFindingACashStarByUserId()

        thenTheCashStartByUserIdIsSuccessfullyFound()
    }

    private fun givenSpringCashStartEndDao() {
        springCashStartEndDao = mock()
        `when`(springCashStartEndDao.save(CASH_START_END.toRepresentation())).thenReturn(CASH_START_END.toRepresentation())
        `when`(springCashStartEndDao.findByCashIdAndEndDate(0L)).thenReturn(CASH_START_NOT_CLOSED.toRepresentation())
        `when`(springCashStartEndDao.findByUserId(0L)).thenReturn(listOf(CASH_START_NOT_CLOSED.toRepresentation(), CASH_START_NOT_CLOSED.toRepresentation()))
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = JpaCashStartEndRepository(springCashStartEndDao)
    }

    private fun whenSavingCashStartEnd() {
        savedCashStartEnd = cashStartEndRepository.save(CASH_START_END)
    }

    private fun whenFindingACashStarNotClosed() {
        cashStartEndNotClosedFound = cashStartEndRepository.findByCashIdAndEndDate(0L)
        CASH_START_NOT_CLOSED.closeDate = cashStartEndNotClosedFound.closeDate
    }

    private fun whenFindingACashStarByUserId() {
        cashStartEndNotClosedFoundByUser = cashStartEndRepository.findByUserId(0L)
    }

    private fun thenTheCashStartEndIsSuccessfullySaved() {
        verify(springCashStartEndDao).save(CASH_START_END.toRepresentation())
        then(savedCashStartEnd).isEqualTo(CASH_START_END)
    }

    private fun thenTheCashStartNotCloseIsSuccessfullyFound() {
        verify(springCashStartEndDao).findByCashIdAndEndDate(0L)
        then(cashStartEndNotClosedFound).isEqualTo(CASH_START_NOT_CLOSED)
    }

    private fun thenTheCashStartByUserIdIsSuccessfullyFound() {
        verify(springCashStartEndDao).findByUserId(0L)
        then(cashStartEndNotClosedFoundByUser).isEqualTo(listOf(CASH_START_NOT_CLOSED, CASH_START_END))
    }

    private companion object {
        private val CASH_START_END = CashStartEnd(0L, 0L, Date(), 0.0, 0L,null,0.0,0.0, Date())
        private val CASH_START_NOT_CLOSED = CashStartEnd(0L, 0L, Date(), 0.0, 0L,Date(),0.0,0.0, Date())

    }

    private fun CashStartEnd.toRepresentation(): CashStartEndRepresentation {
        return CashStartEndRepresentation(id, cashId, openDate, openingBalance, userId, null, realBalance, theoreticalBalance, date)
    }
}
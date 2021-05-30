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

    @Test
    fun `save a cash start-end`() {
        givenSpringCashStartEndDao()
        givenCashStartEndRepository()

        whenSavingCashStartEnd()

        thenTheCashStartEndIsSuccessfullySaved()
    }

    private fun givenSpringCashStartEndDao() {
        springCashStartEndDao = mock()
        `when`(springCashStartEndDao.save(CASH_START_END.toRepresentation())).thenReturn(CASH_START_END.toRepresentation())
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = JpaCashStartEndRepository(springCashStartEndDao)
    }

    private fun whenSavingCashStartEnd() {
        savedCashStartEnd = cashStartEndRepository.save(CASH_START_END)
    }

    private fun thenTheCashStartEndIsSuccessfullySaved() {
        verify(springCashStartEndDao).save(CASH_START_END.toRepresentation())
        then(savedCashStartEnd).isNotNull
    }

    private companion object {
        private val CASH_START_END = CashStartEnd(0L, 0L, Date(), 0.0, 0L,Date(),0.0,0.0, Date())
    }

    private fun CashStartEnd.toRepresentation(): CashStartEndRepresentation {
        return CashStartEndRepresentation(id, cashId, openDate, openingBalance, userId, closeDate, realBalance, theoreticalBalance, date)
    }
}
package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.JpaCashRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.SpringCashDao
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaCashRepositoryShould {

    private lateinit var springCashDao: SpringCashDao
    private lateinit var cashRepository: CashRepository

    private lateinit var savedCash: Cash
    private var cashFound: Cash? = null

    @Test
    fun `save a cash`() {
        givenSpringCashDao()
        givenCashRepository()

        whenSavingCash()

        thenTheCashIsSuccessfullySaved()
    }

    @Test
    fun `find a cash by id`() {
        givenSpringCashDao()
        givenCashRepository()

        whenFindingTheCash(CASH.id)

        thenTheCashIsSuccessfullyFound()
    }

    @Test
    fun `not find a cash by id`() {
        givenSpringCashDao()
        givenCashRepository()

        whenFindingTheCash(1L)

        thenTheCashIsNotFound()
    }

    private fun givenSpringCashDao() {
        springCashDao = mock()
        `when`(springCashDao.save(CASH_REPRESENTATION)).thenReturn(CASH_REPRESENTATION)
        `when`(springCashDao.findById(0L)).thenReturn(Optional.of(CASH_REPRESENTATION))
        `when`(springCashDao.findById(1L)).thenReturn(Optional.empty())
    }

    private fun givenCashRepository() {
        cashRepository = JpaCashRepository(springCashDao)
    }

    private fun whenSavingCash() {
        savedCash = cashRepository.save(CASH)
    }

    private fun whenFindingTheCash(cashId: Long) {
        cashFound = cashRepository.findById(cashId)
    }

    private fun thenTheCashIsSuccessfullySaved() {
        verify(springCashDao).save(CASH_REPRESENTATION)
        then(savedCash).isEqualTo(CASH)
    }

    private fun thenTheCashIsSuccessfullyFound() {
        verify(springCashDao).findById(0L)
        then(cashFound).isEqualTo(CASH)
    }

    private fun thenTheCashIsNotFound() {
        verify(springCashDao).findById(1L)
        then(cashFound).isEqualTo(null)
    }

    private companion object {
        private val CASH = Cash(0L, 0L, 0L, "CLOSE")
        private val CASH_REPRESENTATION = CashRepresentation(0L, 0L, 0L, "CLOSE")
    }
}
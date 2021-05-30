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

class JpaCashRepositoryShould {

    private lateinit var springCashDao: SpringCashDao
    private lateinit var cashRepository: CashRepository

    private lateinit var savedCash: Cash

    @Test
    fun `save a cash`() {
        givenSpringCashDao()
        givenCashRepository()

        whenSavingCash()

        thenTheCashIsSuccessfullySaved()
    }

    private fun givenSpringCashDao() {
        springCashDao = mock()
        `when`(springCashDao.save(CASH_REPRESENTATION)).thenReturn(CASH_REPRESENTATION)
    }

    private fun givenCashRepository() {
        cashRepository = JpaCashRepository(springCashDao)
    }

    private fun whenSavingCash() {
        savedCash = cashRepository.save(CASH)
    }

    private fun thenTheCashIsSuccessfullySaved() {
        verify(springCashDao).save(CASH_REPRESENTATION)
        then(savedCash).isNotNull
    }

    private companion object {
        private val CASH = Cash(0L, 0L, 0L, "CLOSE")
        private val CASH_REPRESENTATION = CashRepresentation(0L, 0L, 0L, "CLOSE")
    }
}
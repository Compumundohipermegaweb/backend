package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetAllRegisterCash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllRegisterCashShould {
    private lateinit var cashRepository: CashRepository
    private lateinit var getAllRegisterCash: GetAllRegisterCash

    private lateinit var allCash: List<Cash>

    @Test
    fun `find all cash`() {
        givenCashRepository()
        givenGetAllRegisterCash()

        whenFindingAllCash()

        thenAllCashAreSuccessfullyFound()
    }

    private fun givenCashRepository() {
        cashRepository = mock()
        `when`(cashRepository.findAll()).thenReturn(listOf(CASH, ANOTHER_CASH))
    }

    private fun givenGetAllRegisterCash() {
        getAllRegisterCash = GetAllRegisterCash(cashRepository)
    }

    private fun whenFindingAllCash() {
        allCash = getAllRegisterCash.invoke()
    }

    private fun thenAllCashAreSuccessfullyFound() {
        verify(cashRepository).findAll()
        then(allCash).isEqualTo(listOf(CASH, ANOTHER_CASH))
    }

    private companion object {
        private val CASH =  Cash(0L, 0L, 0L, "OPEN")
        private val ANOTHER_CASH =  Cash(1L, 1L, 1L, "CLOSE")
    }
}
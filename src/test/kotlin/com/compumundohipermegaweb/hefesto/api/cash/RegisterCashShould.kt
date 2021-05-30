package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.RegisterCash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class RegisterCashShould {
    private lateinit var cashRepository: CashRepository

    private lateinit var registerCash: RegisterCash

    private lateinit var savedCash: Cash

    @Test
    fun `register a cash`() {
        givenCashRepository()
        givenRegisterCash()

        whenRegisterCash()

        thenTheCashIsSuccessfullyRegister()
    }

    private fun givenCashRepository() {
        cashRepository = mock()
        `when`(cashRepository.save(CASH_TO_REGISTER)).thenReturn(CASH_TO_REGISTER)
    }

    private fun givenRegisterCash() {
        registerCash = RegisterCash(cashRepository)
    }

    private fun whenRegisterCash() {
        savedCash = registerCash.invoke(CASH_REQUEST)
    }

    private fun thenTheCashIsSuccessfullyRegister() {
        verify(cashRepository).save(CASH_TO_REGISTER)
        then(savedCash).isEqualTo(CASH_TO_REGISTER)
    }

    private companion object {
        private val CASH_REQUEST =  CashRequest(0L, 0L, 0L, "CLOSE")
        private val CASH_TO_REGISTER =  Cash(0L, 0L, 0L, "CLOSE")
    }
}
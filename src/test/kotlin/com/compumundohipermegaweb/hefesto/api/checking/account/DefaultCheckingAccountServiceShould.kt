package com.compumundohipermegaweb.hefesto.api.checking.account

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.DefaultCheckingAccountService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultCheckingAccountServiceShould {

    private lateinit var checkingAccountRepository: CheckingAccountRepository
    private lateinit var checkingAccountService: CheckingAccountService

    @Test
    fun `increase checking account balance due when discounting from it`() {
        givenCheckingAccountRepository()
        givenCheckingAccountService()

        whenDiscountingBalance(toClient = CLIENT_ID, amount = 120.0)

        thenBalanceDueHasHasBeenIncreased(CHECKING_ACCOUNT, by = 120.0)
    }

    @Test
    fun `decrease checking account balance when discounting from it`() {
        givenCheckingAccountRepository()
        givenCheckingAccountService()

        whenDiscountingBalance(toClient = CLIENT_ID, amount = 300.0)

        thenBalanceHasBeenDecreased(CHECKING_ACCOUNT, 300.0)
    }

    private fun givenCheckingAccountRepository() {
        checkingAccountRepository = mock()
        `when`(checkingAccountRepository.findCheckingAccountByClientId(CLIENT_ID)).thenReturn(CHECKING_ACCOUNT)
    }

    private fun givenCheckingAccountService() {
        checkingAccountService = DefaultCheckingAccountService(checkingAccountRepository)
    }

    private fun whenDiscountingBalance(toClient: Long, amount: Double) {
        checkingAccountService.discount(toClient, amount)
    }

    private fun thenBalanceDueHasHasBeenIncreased(checkingAccount: CheckingAccount, by: Double) {
        verify(checkingAccountRepository).updateBalanceDue(1L, checkingAccount.balanceDue + by)
    }

    private fun thenBalanceHasBeenDecreased(checkingAccount: CheckingAccount, amount: Double) {
        verify(checkingAccountRepository).updateBalance(checkingAccount.clientId, checkingAccount.balance - amount)
    }

    private companion object {
        const val CLIENT_ID = 1L
        val CHECKING_ACCOUNT = CheckingAccount(1L, CLIENT_ID, 500.0, 100.0, 400.0)
    }
}
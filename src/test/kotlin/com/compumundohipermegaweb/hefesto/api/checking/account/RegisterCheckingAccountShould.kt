package com.compumundohipermegaweb.hefesto.api.checking.account

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.action.RegisterCheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService

import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class RegisterCheckingAccountShould {

    private lateinit var checkingAccount: CheckingAccount
    private lateinit var registerCheckingAccount: RegisterCheckingAccount
    private lateinit var checkingAccountService: CheckingAccountService

    @Test
    fun `register a checking Account for a client`(){

        givenCheckingAccountRepository()
        givenRegisterCheckingAccount()
        whenRegisteringCheckingAccount()
        thenTheCheckingAccountIsRegisteredSuccessfully()

    }

    private fun thenTheCheckingAccountIsRegisteredSuccessfully() {
        verify(checkingAccountService).save(CLIENT_ID, CREDIT_LIMIT)
        then(registerCheckingAccount).isNotNull
    }

    private fun whenRegisteringCheckingAccount() {
        checkingAccount = registerCheckingAccount.invoke(0L, 100.0)
    }

    private fun givenRegisterCheckingAccount() {
        registerCheckingAccount = RegisterCheckingAccount(checkingAccountService)
    }

    private fun givenCheckingAccountRepository() {
        checkingAccountService = mock()
        `when`(checkingAccountService.save(CLIENT_ID, CREDIT_LIMIT)).thenReturn(CHECKING_ACCOUNT)
    }

    companion object{
       val CLIENT_ID = 0L
       val CREDIT_LIMIT = 100.00
       val CHECKING_ACCOUNT= CheckingAccount(0L,CLIENT_ID,CREDIT_LIMIT,0.0,0.0)
    }

}
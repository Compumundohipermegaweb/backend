package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.action.GetBalanceByClientId
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetBalanceByClientIdShould {
    private lateinit var clientService : ClientService
    private lateinit var checkingAccountService: CheckingAccountService
    private lateinit var getBalanceByClientId : GetBalanceByClientId
    private var checkingAccount : CheckingAccount? =null
   

    @Test
    fun `return the client balance`(){
        givenClientService()
        givenCheckingAccountService()
        givenBalanceByClientId()
        whenClientBalanceFound()
        thenTheClientBalanceIsReturned()

    }

    private fun givenClientService() {
        clientService = mock()
        `when`(clientService.findById(CLIENT_ID)).thenReturn(CLIENT)
    }

    private fun givenCheckingAccountService() {
        checkingAccountService = mock()
        `when`(checkingAccountService.findCheckingAccountByClientId(CLIENT_ID)).thenReturn(CHECKING_ACCOUNT)
    }

    private fun givenBalanceByClientId() {
        getBalanceByClientId = GetBalanceByClientId(checkingAccountService, clientService)
    }

    private fun whenClientBalanceFound() {
        checkingAccount = getBalanceByClientId.invoke(CLIENT_ID)
    }

    private fun thenTheClientBalanceIsReturned() {
        then(checkingAccount).isEqualTo(CHECKING_ACCOUNT)
    }


    companion object{
        const val CLIENT_ID = 2L
        val CLIENT = Client(2L,"99999999","","","ACTIVO",1000.0,"","")
        val CHECKING_ACCOUNT = CheckingAccount(0L,2L,1000.00,300.00,700.00)

    }
}

package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService

class GetBalanceByClientId (private val checkingAccountService: CheckingAccountService,
                            private val clientService : ClientService){
    operator fun invoke(clientId: Long): CheckingAccount? {
        val client = clientService.findById(clientId)

        if (client != null) {
            return checkingAccountService.findCheckingAccountByClientId(client.id)
        }else {
            return null
        }
    }


}
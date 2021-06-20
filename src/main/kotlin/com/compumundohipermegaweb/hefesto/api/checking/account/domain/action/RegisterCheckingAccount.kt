package com.compumundohipermegaweb.hefesto.api.checking.account.domain.action


import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService

class RegisterCheckingAccount (private val checkingAccountService: CheckingAccountService) {
        operator fun invoke(clientId: Long, creditLimit: Double) : CheckingAccount{
                return checkingAccountService.save(clientId, creditLimit)
        }


}

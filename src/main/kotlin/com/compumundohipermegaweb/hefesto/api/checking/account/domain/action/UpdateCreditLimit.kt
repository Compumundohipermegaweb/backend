package com.compumundohipermegaweb.hefesto.api.checking.account.domain.action

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.checking.account.rest.request.CheckingAccountRequest

class UpdateCreditLimit (private val checkingAccountService: CheckingAccountService){
    operator fun invoke(checkingAccountRequest: CheckingAccountRequest): CheckingAccount? {
        val checkAccClient = checkingAccountService.findCheckingAccountByClientId(checkingAccountRequest.clientId)
        if (checkAccClient != null) {
            if (checkAccClient.balanceDue < checkingAccountRequest.creditLimit) {
                return checkingAccountService.updateCreditLimitByClient(
                    checkingAccountRequest.clientId,
                    checkingAccountRequest.creditLimit
                )
            }

        }
        return null
    }

}
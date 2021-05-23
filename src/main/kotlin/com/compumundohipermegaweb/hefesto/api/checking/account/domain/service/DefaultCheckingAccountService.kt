package com.compumundohipermegaweb.hefesto.api.checking.account.domain.service

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository

class DefaultCheckingAccountService(private val checkingAccountRepository: CheckingAccountRepository): CheckingAccountService {
    override fun findCheckingAccountByClientId(clientId: Long): CheckingAccount? {
        return checkingAccountRepository.findCheckingAccountByClientId(clientId)
    }

    override fun discount(clientId: Long, amount: Double) {
        val checkingAccount = checkingAccountRepository.findCheckingAccountByClientId(clientId)
        if (checkingAccount != null) {
            checkingAccountRepository.updateBalanceDue(clientId, checkingAccount.balanceDue + amount)
            checkingAccountRepository.updateBalance(clientId, checkingAccount.balance - amount)
        }
    }
}
package com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount

interface CheckingAccountRepository {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccount?
    fun updateBalanceDue(clientId: Long, amount: Double)
    fun updateBalance(clientId: Long, amount: Double)
}
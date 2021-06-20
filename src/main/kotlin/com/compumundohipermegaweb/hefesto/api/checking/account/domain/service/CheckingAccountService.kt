package com.compumundohipermegaweb.hefesto.api.checking.account.domain.service

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount

interface CheckingAccountService {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccount?
    fun discount(clientId: Long, amount: Double)
    fun save(clientId: Long, creditLimit: Double): CheckingAccount
    fun updateCreditLimitByClient(clientId: Long, creditLimit: Double): CheckingAccount

}
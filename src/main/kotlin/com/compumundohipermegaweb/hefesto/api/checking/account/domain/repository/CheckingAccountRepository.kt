package com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier

interface CheckingAccountRepository {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccount?
    fun updateBalanceDue(clientId: Long, amount: Double)
    fun updateBalance(clientId: Long, amount: Double)
    fun updateCreditLimit(clientId: Long, creditLimit: Double)
    fun save(clientId: Long, creditLimit: Double): CheckingAccount
}
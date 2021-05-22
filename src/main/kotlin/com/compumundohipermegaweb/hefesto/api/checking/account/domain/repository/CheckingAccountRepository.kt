package com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount

interface CheckingAccountRepository {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccount?
}
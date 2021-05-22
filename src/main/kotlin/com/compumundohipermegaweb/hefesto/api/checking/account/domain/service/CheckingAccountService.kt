package com.compumundohipermegaweb.hefesto.api.checking.account.domain.service

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount

interface CheckingAccountService {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccount?
}
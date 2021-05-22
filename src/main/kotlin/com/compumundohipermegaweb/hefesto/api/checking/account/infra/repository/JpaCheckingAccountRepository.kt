package com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation

class JpaCheckingAccountRepository(private val springDataCheckingAccountDao: SpringDataCheckingAccountDao): CheckingAccountRepository {
    override fun findCheckingAccountByClientId(clientId: Long): CheckingAccount? {
        val checkingAccountRepresentation = springDataCheckingAccountDao.findCheckingAccountByClientId(clientId)
        if(checkingAccountRepresentation != null) {
            return checkingAccountRepresentation.toCheckingAccount()
        }
        return null
    }

    private fun CheckingAccountRepresentation.toCheckingAccount(): CheckingAccount {
        return CheckingAccount(id, clientId, creditLimit, balanceDue, balance)
    }
}



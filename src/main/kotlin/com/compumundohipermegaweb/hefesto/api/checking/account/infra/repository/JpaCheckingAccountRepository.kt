package com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation

class JpaCheckingAccountRepository(private val springDataCheckingAccountDao: SpringDataCheckingAccountDao): CheckingAccountRepository {
    override fun findCheckingAccountByClientId(clientId: Long): CheckingAccount? {
        val checkingAccountRepresentation = springDataCheckingAccountDao.findByClientId(clientId)
        return checkingAccountRepresentation?.toCheckingAccount()
    }

    override fun updateBalanceDue(clientId: Long, amount: Double) {
        springDataCheckingAccountDao.updateBalanceDueByClient(clientId, amount)
    }

    override fun updateBalance(clientId: Long, amount: Double) {
        springDataCheckingAccountDao.updateBalanceByClient(clientId, amount)
    }

    override fun updateCreditLimit(clientId: Long, creditLimit: Double){
        springDataCheckingAccountDao.updateCreditLimitByClient(clientId, creditLimit)
    }

    override fun save(clientId: Long, creditLimit: Double): CheckingAccount{
            val checkingAccountRepresentation=  CheckingAccountRepresentation(0L,clientId,creditLimit,0.0,creditLimit)
            return springDataCheckingAccountDao.save(checkingAccountRepresentation).toCheckingAccount()
    }

    private fun CheckingAccountRepresentation.toCheckingAccount(): CheckingAccount {
        return CheckingAccount(id, clientId, creditLimit, balanceDue, balance)
    }


}



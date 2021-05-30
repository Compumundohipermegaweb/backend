package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashRepresentation

class JpaCashRepository(private val springCashDao: SpringCashDao): CashRepository {
    override fun save(cash: Cash): Cash {
        return springCashDao.save(cash.toRepresentation()).toCash()
    }

    private fun Cash.toRepresentation(): CashRepresentation {
        return CashRepresentation(id, branchId, pointOfSale, status)
    }

    private fun CashRepresentation.toCash(): Cash {
        return Cash(id, branchId, pointOfSale, status)
    }
}





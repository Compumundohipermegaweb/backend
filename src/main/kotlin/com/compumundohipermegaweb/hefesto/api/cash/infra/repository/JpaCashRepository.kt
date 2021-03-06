package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashRepresentation

class JpaCashRepository(private val springCashDao: SpringCashDao): CashRepository {
    override fun save(cash: Cash): Cash {
        return springCashDao.save(cash.toRepresentation()).toCash()
    }

    override fun findById(cashId: Long): Cash? {
        val cashRepresentation = springCashDao.findById(cashId)
        if(cashRepresentation.isPresent) {
            return cashRepresentation.get().toCash()
        }
        return null
    }

    override fun findAll(): List<Cash> {
        return springCashDao.findAll().map { it.toCash() }.toList()
    }

    override fun findByBranchId(branchId: Long): Cash? {
        val representation = springCashDao.findByBranchId(branchId)
        return representation?.toCash()
    }

    private fun Cash.toRepresentation(): CashRepresentation {
        return CashRepresentation(id, branchId, pointOfSale, status)
    }

    private fun CashRepresentation.toCash(): Cash {
        return Cash(id, branchId, pointOfSale, status)
    }
}





package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashStartEndRepresentation

class JpaCashStartEndRepository(private val springCashStartEndDao: SpringCashStartEndDao): CashStartEndRepository {

    override fun save(cashStartEnd: CashStartEnd): CashStartEnd {
        return springCashStartEndDao.save(cashStartEnd.toRepresentation()).toCashStartEnd()
    }

    override fun findByCashIdAndEndDate(cashId: Long): CashStartEnd {
        return springCashStartEndDao.findByCashIdAndEndDate(cashId).toCashStartEnd()
    }

    override fun findByUserId(userId: Long): List<CashStartEnd> {
        return springCashStartEndDao.findByUserId(userId).map { it.toCashStartEnd() }
    }

    private fun CashStartEnd.toRepresentation(): CashStartEndRepresentation {
        return CashStartEndRepresentation(id, cashId, openDate, openingBalance, userId, closeDate, realBalance, theoreticalBalance, date)
    }

    private fun CashStartEndRepresentation.toCashStartEnd(): CashStartEnd {
        return CashStartEnd(id, cashId, openDate, openingBalance, userId, closeDate, realBalance, theoreticalBalance, date)
    }
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd

interface CashStartEndRepository {
    fun save(cashStartEnd: CashStartEnd): CashStartEnd
    fun findByCashIdAndEndDate(cashId: Long): CashStartEnd
    fun findByUserId(userId: Long): List<CashStartEnd>
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash

interface CashRepository {
    fun save(cash: Cash): Cash
}
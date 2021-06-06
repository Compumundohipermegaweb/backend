package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository

class GetCashByUserId(private val cashStartEndRepository: CashStartEndRepository) {
    operator fun invoke(userId: Long): Long {
        val cash = cashStartEndRepository.findByUserId(userId)
        if(cash.isNotEmpty()) {
            return cash[0].id
        }
        return 404L
    }
}
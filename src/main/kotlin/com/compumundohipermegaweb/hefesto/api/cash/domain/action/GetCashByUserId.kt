package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository

class GetCashByUserId(private val cashStartEndRepository: CashStartEndRepository) {
    operator fun invoke(userId: Long): Long {
        var cash = cashStartEndRepository.findByUserId(userId).filter { it.closeDate == null }.toList()
        if(cash.isEmpty()) {
            return 0L
        }
        return cash[0].id
    }
}
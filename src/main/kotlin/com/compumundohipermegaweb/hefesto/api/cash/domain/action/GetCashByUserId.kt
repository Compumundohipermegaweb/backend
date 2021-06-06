package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository

class GetCashByUserId(private val cashStartEndRepository: CashStartEndRepository) {
    operator fun invoke(userId: Long): Long {
        return cashStartEndRepository.findByUserId(userId).filter { it.closeDate == null }.toList()[0].id
    }
}
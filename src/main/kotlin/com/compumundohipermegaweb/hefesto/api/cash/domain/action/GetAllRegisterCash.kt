package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository

class GetAllRegisterCash(private val cashRepository: CashRepository) {
    operator fun invoke(): List<Cash> {
        return cashRepository.findAll()
    }
}
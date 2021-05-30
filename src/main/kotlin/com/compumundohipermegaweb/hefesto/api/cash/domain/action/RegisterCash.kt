package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashRequest

class RegisterCash(private val cashRepository: CashRepository) {
    operator fun invoke(cashRequest: CashRequest): Cash {
        return cashRepository.save(cashRequest.toCash())
    }

    private fun CashRequest.toCash(): Cash {
        return Cash(id, branchId, pointOfSale, status)
    }
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository

class GetAllCashMovements(private val cashMovementRepository: CashMovementRepository) {
    operator fun invoke(cashStartEndId: Long): List<CashMovement> {
        return cashMovementRepository.findByCashStartEndId(cashStartEndId)
    }
}
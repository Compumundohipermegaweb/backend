package com.compumundohipermegaweb.hefesto.api.cash.domain.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement

interface CashMovementRepository {
    fun save(cashMovement: CashMovement): CashMovement
}
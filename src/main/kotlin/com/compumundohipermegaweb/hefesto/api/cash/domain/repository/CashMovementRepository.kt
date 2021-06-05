package com.compumundohipermegaweb.hefesto.api.cash.domain.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement

interface CashMovementRepository {
    fun save(cashMovement: CashMovement, cashStartEndId: Long): CashMovement
    fun findByCashStartEndId(cashStartEndId: Long): List<CashMovement>
    fun findById(movementId: Long): CashMovement?
}
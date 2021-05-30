package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashMovementRepresentation

class JpaCashMovementRepository(private val springCashMovementDao: SpringCashMovementDao): CashMovementRepository {
    override fun save(cashMovement: CashMovement): CashMovement {
        return springCashMovementDao.save(cashMovement.toRepresentation()).toCashMovement()
    }

    private fun CashMovement.toRepresentation(): CashMovementRepresentation {
        return CashMovementRepresentation(id, movementType, dateTime, transactionId, paymentMethodId, cardId, userId, amount, detail)
    }

    private fun CashMovementRepresentation.toCashMovement(): CashMovement {
        return CashMovement(id, movementType, dateTime, transactionId, paymentMethodId, cardId, userId, amount, detail)
    }
}



package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashMovementRepresentation

class JpaCashMovementRepository(private val springCashMovementDao: SpringCashMovementDao): CashMovementRepository {
    override fun save(cashMovement: CashMovement, cashStartEndId: Long): CashMovement {
        return springCashMovementDao.save(cashMovement.toRepresentation(cashStartEndId)).toCashMovement()
    }

    override fun findByCashStartEndId(cashStartEndId: Long): List<CashMovement> {
        return springCashMovementDao.findBycashStartEndId(cashStartEndId).map { it.toCashMovement() }
    }

    override fun findById(movementId: Long): CashMovement? {
        val cashMovement = springCashMovementDao.findById(movementId)
        if(cashMovement.isPresent) {
            return cashMovement.get().toCashMovement()
        }
        return null
    }

    private fun CashMovement.toRepresentation(cashStartEndId: Long): CashMovementRepresentation {
        return CashMovementRepresentation(id, cashStartEndId, movementType, dateTime, sourceId, sourceDescription, paymentMethodId, cardId, userId, amount, detail)
    }

    private fun CashMovementRepresentation.toCashMovement(): CashMovement {
        return CashMovement(id, cashStartEndId, movementType, dateTime, sourceId, sourceDescription, paymentMethodId, cardId, userId, amount, detail)
    }
}



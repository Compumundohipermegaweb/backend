package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.TotalMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.TotalMovementRepository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.TotalMovementRepresentation


class JpaTotalMovementRepository(private val springTotalMovementDao: SpringTotalMovementDao): TotalMovementRepository {

    override fun findByBranchId(branchId: Long): List<TotalMovement> {
        return springTotalMovementDao.findByBranchId(branchId).map { it.toTotalMovement() }
    }

    private fun TotalMovementRepresentation.toTotalMovement(): TotalMovement {
        return TotalMovement(branchId,cashId,cashStartEndId,dateTime, movementType, paymentMethod, total)
    }
}
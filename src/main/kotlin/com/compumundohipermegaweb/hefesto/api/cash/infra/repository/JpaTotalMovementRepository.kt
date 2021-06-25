package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.TotalMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.TotalMovementRepository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.TotalMovementRepresentation


class JpaTotalMovementRepository(private val springTotalMovementDao: SpringTotalMovementDao): TotalMovementRepository {

    override fun findAll(branchId: Long): List<TotalMovement> {
        return springTotalMovementDao.findAll(branchId).map { it.toTotalMovement() }
    }

    private fun TotalMovementRepresentation.toTotalMovement(): TotalMovement {
        return TotalMovement(branchId,cashId,cashStartEndId,dateTime,movementType,source,paymentMethod,card,digits,detail,total,level)
    }
}
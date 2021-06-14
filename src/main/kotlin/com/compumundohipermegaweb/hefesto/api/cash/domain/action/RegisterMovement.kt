package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashMovementRequest

import java.util.*

class RegisterMovement (private val cashMovementRepository: CashMovementRepository) {

    operator fun invoke(cashMovementRequest: CashMovementRequest): CashMovement{
       return  cashMovementRepository.save(cashMovementRequest.toCashMovement(),cashMovementRequest.cashStartEndId)
    }

    private fun CashMovementRequest.toCashMovement(): CashMovement {
        return CashMovement(0L,cashStartEndId,movementType, Date(),sourceId,sourceDescription,0L,0L,userId, amount, detail)
    }
}
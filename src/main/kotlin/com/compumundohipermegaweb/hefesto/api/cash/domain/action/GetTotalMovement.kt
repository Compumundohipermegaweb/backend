package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.TotalMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.TotalMovementRepository


class GetTotalMovement(private val totalMovementRepository: TotalMovementRepository){
    operator fun invoke(branchId: Long): List<TotalMovement>{
        return totalMovementRepository.findByBranchId(branchId).filter { it.paymentMethod == "Efectivo" }
    }
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.repository

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.TotalMovement


interface TotalMovementRepository {
    fun findByBranchId(branchId: Long): List<TotalMovement>
}
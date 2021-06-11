package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.TotalMovementRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringTotalMovementDao: CrudRepository<TotalMovementRepresentation, Long> {
    fun findByBranchId(branchId: Long): List<TotalMovementRepresentation>
}
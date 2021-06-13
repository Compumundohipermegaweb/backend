package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.TotalMovementRepresentation

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringTotalMovementDao: CrudRepository<TotalMovementRepresentation, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM VW_TOTAL_MOVEMENT WHERE BRANCH_ID= ?1")
    fun findAll(branchId: Long): List<TotalMovementRepresentation>
}
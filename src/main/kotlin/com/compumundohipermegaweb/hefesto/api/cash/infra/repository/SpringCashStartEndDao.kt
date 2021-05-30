package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashStartEndRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringCashStartEndDao: CrudRepository<CashStartEndRepresentation, Long> {
    @Query("SELECT * FROM CASH_START_END WHERE CASH_ID = ?1 AND CLOSE_DATE IS NULL", nativeQuery = true)
    fun findByCashIdAndEndDate(cashId: Long): CashStartEndRepresentation
}
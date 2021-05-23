package com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataCheckingAccountDao: CrudRepository<CheckingAccountRepresentation, Long> {
    fun findByClientId(clientId: Long): CheckingAccountRepresentation?

    @Query("update CheckingAccountRepresentation c set c.balanceDue = :amount where c.clientId = :clientId")
    fun updateBalanceDueByClient(clientId: Long, amount: Double)

    @Query("update CheckingAccountRepresentation c set c.balance = :amount where c.clientId = :clientId")
    fun updateBalanceByClient(clientId: Long, amount: Double)
}
package com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface SpringDataCheckingAccountDao: CrudRepository<CheckingAccountRepresentation, Long> {
    fun findByClientId(clientId: Long): CheckingAccountRepresentation?

    @Transactional
    @Modifying
    @Query("update CheckingAccountRepresentation c set c.balanceDue = :amount where c.clientId = :clientId")
    fun updateBalanceDueByClient(clientId: Long, amount: Double)

    @Transactional
    @Modifying
    @Query("update CheckingAccountRepresentation c set c.balance = :amount where c.clientId = :clientId")
    fun updateBalanceByClient(clientId: Long, amount: Double)

    @Transactional
    @Modifying
    @Query("update CheckingAccountRepresentation c set c.creditLimit = :creditLimit, c.balance = (:creditLimit - c.balanceDue) where c.clientId = :clientId")
    fun updateCreditLimitByClient(clientId: Long, creditLimit: Double)
}
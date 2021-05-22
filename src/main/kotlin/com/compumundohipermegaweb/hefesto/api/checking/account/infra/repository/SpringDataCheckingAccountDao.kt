package com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository

import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringDataCheckingAccountDao: CrudRepository<CheckingAccountRepresentation, Long> {
    fun findCheckingAccountByClientId(clientId: Long): CheckingAccountRepresentation
}
package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringCashDao: CrudRepository<CashRepresentation, Long>
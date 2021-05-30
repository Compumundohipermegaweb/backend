package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashMovementRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringCashMovementDao: CrudRepository<CashMovementRepresentation, Long>
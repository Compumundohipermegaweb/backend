package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashStartEndRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringCashStartEndDao: CrudRepository<CashStartEndRepresentation, Long>
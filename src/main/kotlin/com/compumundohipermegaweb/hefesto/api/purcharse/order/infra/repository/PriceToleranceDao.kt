package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PriceToleranceRepresentation
import org.springframework.data.repository.CrudRepository

interface PriceToleranceDao: CrudRepository<PriceToleranceRepresentation, Long>

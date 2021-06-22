package com.compumundohipermegaweb.hefesto.api.discount.infra.repository

import org.springframework.data.repository.CrudRepository

interface DiscountDao: CrudRepository<DiscountRepresentation, Long>

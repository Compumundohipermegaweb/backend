package com.compumundohipermegaweb.hefesto.api.card.infra.repository

import com.compumundohipermegaweb.hefesto.api.card.infra.representation.CardRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringDataCardDao: CrudRepository<CardRepresentation, Long>
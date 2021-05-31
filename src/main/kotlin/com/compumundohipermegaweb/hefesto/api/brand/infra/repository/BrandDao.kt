package com.compumundohipermegaweb.hefesto.api.brand.infra.repository

import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandRepresentation
import org.springframework.data.repository.CrudRepository

interface BrandDao: CrudRepository<BrandRepresentation, Long>

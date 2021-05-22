package com.compumundohipermegaweb.hefesto.api.brand.infra.repository

import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandDao
import org.springframework.data.repository.CrudRepository

interface SpringDataBrandClient: CrudRepository<BrandDao, Long>

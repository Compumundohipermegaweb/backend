package com.compumundohipermegaweb.hefesto.api.brand.infra

import org.springframework.data.repository.CrudRepository

interface SpringDataBrandClient: CrudRepository<BrandDao, Long>

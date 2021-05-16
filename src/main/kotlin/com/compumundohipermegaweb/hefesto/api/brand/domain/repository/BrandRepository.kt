package com.compumundohipermegaweb.hefesto.api.brand.domain.repository

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand

interface BrandRepository {
    fun findById(id: Long): Brand?
}
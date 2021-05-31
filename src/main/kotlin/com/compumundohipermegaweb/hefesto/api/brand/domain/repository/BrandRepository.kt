package com.compumundohipermegaweb.hefesto.api.brand.domain.repository

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand

interface BrandRepository {
    fun findById(id: Long): Brand?
    fun findAll(): List<Brand>
    fun delete(brandId: Long)
    fun save(brandToSave: Brand): Brand
}
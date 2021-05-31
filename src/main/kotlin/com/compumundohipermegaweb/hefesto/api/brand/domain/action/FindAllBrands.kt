package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository

class FindAllBrands(private val brandRepository: BrandRepository) {

    operator fun invoke(): List<Brand> {
        return brandRepository.findAll()
    }
}
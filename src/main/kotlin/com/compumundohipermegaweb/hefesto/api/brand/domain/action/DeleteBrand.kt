package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository

class DeleteBrand(private val brandRepository: BrandRepository) {

    operator fun invoke(brandId: Long) {
        brandRepository.delete(brandId)
    }
}
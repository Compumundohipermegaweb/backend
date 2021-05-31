package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository

class CreateBrand(private val brandRepository: BrandRepository) {

    operator fun invoke(actionData: ActionData): Brand {
        val brand = actionData.toBrand()
        return brandRepository.save(brand)
    }

    private fun ActionData.toBrand() = Brand(0L, name)

    data class ActionData(val name: String)
}
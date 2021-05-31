package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository

class UpdateBrand(private val brandRepository: BrandRepository) {

    operator fun invoke(actionData: ActionData): Brand {
        val brand = actionData.toBrand()
        return brandRepository.update(brand)
    }

    private fun ActionData.toBrand() = Brand(id, name)

    data class ActionData(val id: Long, val name: String)
}
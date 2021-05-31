package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class UpdateBrandShould {

    private lateinit var brandRepository: BrandRepository
    private lateinit var updateBrand: UpdateBrand

    private lateinit var brandUpdated: Brand

    @Test
    fun `update the brand`() {
        brandRepository = mock()
        updateBrand = UpdateBrand(brandRepository)

        brandUpdated = updateBrand(ACTION_DATA)

        verify(brandRepository).update(BRAND_TO_UPDATE)
    }

    private companion object {
        val ACTION_DATA = UpdateBrand.ActionData(0L, "")
        val BRAND_TO_UPDATE = Brand(0L, ACTION_DATA.name)
    }
}
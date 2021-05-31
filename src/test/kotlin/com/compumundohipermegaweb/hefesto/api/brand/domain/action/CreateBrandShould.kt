package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class CreateBrandShould {

    private lateinit var brandRepository: BrandRepository
    private lateinit var createBrand: CreateBrand

    private lateinit var createdBrand: Brand

    @Test
    fun `create a new brand`() {
        givenBrandRepository()
        givenCreateBrand()

        whenCreatingBrand()

        thenBrandHasBeenCreated()
    }

    private fun givenBrandRepository() {
        brandRepository = mock()
        `when`(brandRepository.save(BRAND_TO_SAVE)).thenReturn(CREATED_BRAND)
    }

    private fun givenCreateBrand() {
        createBrand = CreateBrand(brandRepository)
    }

    private fun whenCreatingBrand() {
        createdBrand = createBrand(ACTION_DATA)
    }

    private fun thenBrandHasBeenCreated() {
        then(createdBrand).isNotNull
        verify(brandRepository).save(BRAND_TO_SAVE)
    }

    private companion object {
        val ACTION_DATA = CreateBrand.ActionData("Brand Name")
        val BRAND_TO_SAVE = Brand(0L, ACTION_DATA.name)
        val CREATED_BRAND = Brand(0L, BRAND_TO_SAVE.name)
    }
}
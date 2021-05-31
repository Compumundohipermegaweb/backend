package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class FindAllBrandsShould {

    private lateinit var brandRepository: BrandRepository
    private lateinit var findAllBrands: FindAllBrands

    private lateinit var brandsFound: List<Brand>

    @Test
    fun `find all the brands`() {
        givenBrandRepository()
        givenFindAllBrands()

        whenFindingAllBrands()

        thenBrandsWhereFound()
    }

    private fun givenBrandRepository() {
        brandRepository = mock()
    }

    private fun givenFindAllBrands() {
        findAllBrands = FindAllBrands(brandRepository)
    }

    private fun whenFindingAllBrands() {
        brandsFound = findAllBrands()
    }

    private fun thenBrandsWhereFound() {
        verify(brandRepository).findAll()
    }
}
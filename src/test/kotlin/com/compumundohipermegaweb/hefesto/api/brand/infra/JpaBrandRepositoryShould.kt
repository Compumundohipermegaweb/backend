package com.compumundohipermegaweb.hefesto.api.brand.infra

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.BrandDao
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.JpaBrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandRepresentation
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaBrandRepositoryShould {

    private lateinit var brandDao: BrandDao
    private lateinit var brandRepository: BrandRepository

    private var brandFound: Brand? = null
    private lateinit var brandsFound: List<Brand>
    private lateinit var brandCreated: Brand

    @Test
    fun `find by id`() {
        givenBrandDao()
        givenBrandRepository()

        whenFindingBrandById()

        thenBrandFound()
    }

    @Test
    fun `find all brands`() {
        givenBrandDao()
        givenBrandRepository()

        whenFindingAllBrands()

        thenBrandsWhereFound()
    }

    @Test
    fun `delete by id`() {
        givenBrandDao()
        givenBrandRepository()

        whenDeletingBrand()

        thenBrandWasDeleted()
    }

    @Test
    fun `create a brand`() {
        givenBrandDao()
        givenBrandRepository()

        whenCreatingBrand()

        thenBrandWasCreated()
    }

    private fun givenBrandDao() {
        brandDao = mock()
        `when`(brandDao.findById(BRAND_ID)).thenReturn(BRAND_DAO)
        `when`(brandDao.save(any())).thenReturn(BRAND_REPRESENTATION_TO_CREATE)
    }

    private fun givenBrandRepository() {
        brandRepository = JpaBrandRepository(brandDao)
    }

    private fun whenFindingBrandById() {
        brandFound = brandRepository.findById(BRAND_ID)
    }

    private fun whenFindingAllBrands() {
        brandsFound = brandRepository.findAll()
    }

    private fun whenDeletingBrand() {
        brandRepository.delete(1L)
    }

    private fun whenCreatingBrand() {
        brandCreated = brandRepository.save(BRAND_TO_CREATE)
    }

    private fun thenBrandFound() {
        then(brandFound).isEqualTo(EXPECTED_BRAND)
    }

    private fun thenBrandsWhereFound() {
        verify(brandDao).findAll()
    }

    private fun thenBrandWasDeleted() {
        verify(brandDao).deleteById(1L)
    }

    private fun thenBrandWasCreated() {
        verify(brandDao).save(any())
    }

    private companion object {
        const val BRAND_ID = 1L
        val BRAND_DAO = Optional.of(BrandRepresentation(BRAND_ID, "name"))
        val EXPECTED_BRAND = Brand(BRAND_ID, "name")
        val BRAND_TO_CREATE = Brand(0L, "Name")
        val BRAND_REPRESENTATION_TO_CREATE = BrandRepresentation(BRAND_TO_CREATE.id, BRAND_TO_CREATE.name)
    }
}
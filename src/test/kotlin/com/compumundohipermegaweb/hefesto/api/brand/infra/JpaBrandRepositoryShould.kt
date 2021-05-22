package com.compumundohipermegaweb.hefesto.api.brand.infra

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.JpaBrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.SpringDataBrandClient
import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaBrandRepositoryShould {

    private lateinit var springDataBrandClient: SpringDataBrandClient
    private lateinit var brandRepository: BrandRepository

    private var brandFound: Brand? = null

    @Test
    fun `find by id`() {
        givenSpringDataBrandClient()
        givenBrandRepository()

        whenFindingBrandById()

        thenBrandFound()
    }

    private fun givenSpringDataBrandClient() {
        springDataBrandClient = mock()
        `when`(springDataBrandClient.findById(BRAND_ID)).thenReturn(BRAND_DAO)
    }

    private fun givenBrandRepository() {
        brandRepository = JpaBrandRepository(springDataBrandClient)
    }

    private fun whenFindingBrandById() {
        brandFound = brandRepository.findById(BRAND_ID)
    }

    private fun thenBrandFound() {
        then(brandFound).isEqualTo(EXPECTED_BRAND)
    }

    private companion object {
        const val BRAND_ID = 1L
        val BRAND_DAO = Optional.of(BrandDao(BRAND_ID, "name"))
        val EXPECTED_BRAND = Brand(BRAND_ID, "name")
    }
}
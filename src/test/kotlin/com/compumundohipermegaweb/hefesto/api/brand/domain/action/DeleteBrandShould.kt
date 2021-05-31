package com.compumundohipermegaweb.hefesto.api.brand.domain.action

import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hibernate.sql.Delete
import org.junit.jupiter.api.Test

class DeleteBrandShould {

    private lateinit var brandRepository: BrandRepository
    private lateinit var deleteBrand: DeleteBrand

    @Test
    fun `physically delete the brand`() {
        givenBrandRepository()
        givenDeleteBrand()

        whenDeletingBrand()

        thenBrandHasBeenDeleted()
    }

    private fun givenBrandRepository() {
        brandRepository = mock()
    }

    private fun givenDeleteBrand() {
        deleteBrand = DeleteBrand(brandRepository)
    }

    private fun whenDeletingBrand() {
        deleteBrand(1L)
    }

    private fun thenBrandHasBeenDeleted() {
        verify(brandRepository).delete(1L)
    }
}
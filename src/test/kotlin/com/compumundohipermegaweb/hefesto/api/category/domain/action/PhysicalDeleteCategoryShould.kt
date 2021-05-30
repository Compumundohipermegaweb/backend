package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class PhysicalDeleteCategoryShould {

    private lateinit var categoryRepository: CategoryRepository
    private lateinit var physicalDeleteCategory: PhysicalDeleteCategory

    @Test
    fun `delete the category`() {
        givenCategoryRepository()
        givenPhysicalDeleteCategory()

        whenDeletingCategory()

        thenCategoryHasBeenDeleted()
    }

    private fun givenCategoryRepository() {
        categoryRepository = mock()
    }

    private fun givenPhysicalDeleteCategory() {
        physicalDeleteCategory = PhysicalDeleteCategory(categoryRepository)
    }

    private fun whenDeletingCategory() {
        physicalDeleteCategory(categoryId = 1L)
    }

    private fun thenCategoryHasBeenDeleted() {
        verify(categoryRepository).delete(1L)
    }
}
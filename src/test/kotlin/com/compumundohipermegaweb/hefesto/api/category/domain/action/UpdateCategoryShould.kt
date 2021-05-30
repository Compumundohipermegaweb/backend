package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class UpdateCategoryShould {

    private lateinit var categoryRepository: CategoryRepository
    private lateinit var updateCategory: UpdateCategory

    private lateinit var updatedCategory: Category

    @Test
    fun `update the category`() {
        givenCategoryRepository()
        givenUpdateCategory()

        whenUpdatingCategory()

        thenCategoryWasUpdated()
    }

    private fun givenCategoryRepository() {
        categoryRepository = mock()
        `when`(categoryRepository.save(CATEGORY_TO_UPDATE)).thenReturn(CATEGORY_TO_UPDATE)
    }

    private fun givenUpdateCategory() {
        updateCategory = UpdateCategory(categoryRepository)
    }

    private fun whenUpdatingCategory() {
        updatedCategory = updateCategory(ACTION_DATA)
    }

    private fun thenCategoryWasUpdated() {
        verify(categoryRepository).save(CATEGORY_TO_UPDATE)
    }

    private companion object {
        val ACTION_DATA = UpdateCategory.ActionData(0L, "New name", "New Description")
        val CATEGORY_TO_UPDATE = Category(ACTION_DATA.id, ACTION_DATA.name, ACTION_DATA.description)
    }

}
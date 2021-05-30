package com.compumundohipermegaweb.hefesto.api.category.domain

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.compumundohipermegaweb.hefesto.api.category.domain.action.CreateCategory
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

internal class CreateCategoryShould {

    private lateinit var categoryRepository: CategoryRepository
    private lateinit var createCategory: CreateCategory

    private lateinit var categoryCreated: Category

    @Test
    fun `create a category`() {
        givenCategoryRepository()
        givenCreateCategory()

        whenCreatingACategory(with = CreateCategory.ActionData(CATEGORY_TO_SAVE.name, CATEGORY_TO_SAVE.description))

        thenCategoryHasBeenCreated()
    }

    private fun givenCategoryRepository() {
        categoryRepository = mock()
        `when`(categoryRepository.save(CATEGORY_TO_SAVE)).thenReturn(SAVED_CATEGORY)
    }

    private fun givenCreateCategory() {
        createCategory = CreateCategory(categoryRepository)
    }

    private fun whenCreatingACategory(with: CreateCategory.ActionData) {
        categoryCreated = createCategory(with)
    }

    private fun thenCategoryHasBeenCreated() {
        then(categoryCreated.id).isNotNull
        verify(categoryRepository).save(CATEGORY_TO_SAVE)
    }

    private companion object {
        val CATEGORY_TO_SAVE = Category(0L, "category", "Category description")
        val SAVED_CATEGORY = Category(0L, "category", "Category description")
    }
}
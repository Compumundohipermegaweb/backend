package com.compumundohipermegaweb.hefesto.api.category.infra.repository

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.compumundohipermegaweb.hefesto.api.category.infra.representation.CategoryRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaCategoryRepositoryShould {

    private lateinit var categoryDao: CategoryDao
    private lateinit var categoryRepository: CategoryRepository

    @Test
    fun `save the category`() {
        givenCategoryDao()
        givenCategoryRepository()

        whenSavingCategory()

        thenCategoryWasSaved()
    }

    private fun givenCategoryDao() {
        categoryDao = mock()
        `when`(categoryDao.save(CATEGORY_REPRESENTATION_TO_SAVE)).thenReturn(CATEGORY_REPRESENTATION_TO_SAVE)
    }

    private fun givenCategoryRepository() {
        categoryRepository = JpaCategoryRepository(categoryDao)
    }

    private fun whenSavingCategory() {
        categoryRepository.save(CATEGORY_TO_SAVE)
    }

    private fun thenCategoryWasSaved() {
        verify(categoryDao).save(CATEGORY_REPRESENTATION_TO_SAVE)
    }

    private companion object {
        val CATEGORY_TO_SAVE = Category(0L, "", "")
        val CATEGORY_REPRESENTATION_TO_SAVE = CategoryRepresentation(CATEGORY_TO_SAVE.id, CATEGORY_TO_SAVE.name, CATEGORY_TO_SAVE.description)
    }
}
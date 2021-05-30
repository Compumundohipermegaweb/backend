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

    private lateinit var categoriesFound: List<Category>

    @Test
    fun `save the category`() {
        givenCategoryDao()
        givenCategoryRepository()

        whenSavingCategory()

        thenCategoryWasSaved()
    }

    @Test
    fun `find all the categories`() {
        givenCategoryDao()
        givenCategoryRepository()

        whenFindingAllCategories()

        thenCategoriesWhereFound()
    }

    @Test
    fun `delete a category by id`() {
        givenCategoryDao()
        givenCategoryRepository()

        categoryRepository.delete(1L)

        verify(categoryDao).deleteById(1L)
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

    private fun whenFindingAllCategories() {
        categoriesFound = categoryRepository.findAll()
    }

    private fun thenCategoryWasSaved() {
        verify(categoryDao).save(CATEGORY_REPRESENTATION_TO_SAVE)
    }

    private fun thenCategoriesWhereFound() {
        verify(categoryDao).findAll()
    }

    private companion object {
        val CATEGORY_TO_SAVE = Category(0L, "", "")
        val CATEGORY_REPRESENTATION_TO_SAVE = CategoryRepresentation(CATEGORY_TO_SAVE.id, CATEGORY_TO_SAVE.name, CATEGORY_TO_SAVE.description)
    }
}
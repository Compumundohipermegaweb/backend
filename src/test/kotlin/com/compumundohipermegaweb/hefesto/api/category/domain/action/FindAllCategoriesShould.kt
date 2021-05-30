package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.infra.repository.JpaCategoryRepository
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class FindAllCategoriesShould {

    private lateinit var categoryRepository: JpaCategoryRepository
    private lateinit var findAllCategories: FindAllCategories

    private lateinit var categoriesFound: List<Category>

    @Test
    fun `retrieve all the existing categories`() {
        givenCategoryRepository()
        givenFindAllCategories()

        whenFindingAllCategories()

        thenCategoriesWhereFound()
    }

    private fun givenCategoryRepository() {
        categoryRepository = mock()
        `when`(categoryRepository.findAll()).thenReturn(SAVED_CATEGORIES)
    }

    private fun givenFindAllCategories() {
        findAllCategories = FindAllCategories(categoryRepository)
    }

    private fun whenFindingAllCategories() {
        categoriesFound = findAllCategories()
    }

    private fun thenCategoriesWhereFound() {
        verify(categoryRepository).findAll()
        then(categoriesFound).isNotEmpty
    }

    private companion object {
        val SAVED_CATEGORIES = listOf(
                Category(0L, "", ""),
                Category(1L, "", ""),
                Category(2L, "", ""),
                Category(3L, "", "")
        )
    }
}
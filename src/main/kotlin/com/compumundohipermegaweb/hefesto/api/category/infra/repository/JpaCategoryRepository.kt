package com.compumundohipermegaweb.hefesto.api.category.infra.repository

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.compumundohipermegaweb.hefesto.api.category.infra.representation.CategoryRepresentation

class JpaCategoryRepository(private val categoryDao: CategoryDao): CategoryRepository {

    override fun save(category: Category): Category {
        val categoryRepresentation = category.toRepresentation()
        return categoryDao.save(categoryRepresentation).toCategory()
    }

    override fun findAll(): List<Category> {
        return categoryDao.findAllByDeleted(false).map { it.toCategory() }
    }

    override fun delete(categoryId: Long) {
        categoryDao.logicDeleteById(categoryId)
    }

    private fun Category.toRepresentation() = CategoryRepresentation(id, name, description, false)

    private fun CategoryRepresentation.toCategory() = Category(id, name, description)
}
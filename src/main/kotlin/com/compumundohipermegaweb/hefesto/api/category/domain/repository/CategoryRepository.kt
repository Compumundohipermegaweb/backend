package com.compumundohipermegaweb.hefesto.api.category.domain.repository

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category

interface CategoryRepository {
    fun findAll(): List<Category>
    fun delete(categoryId: Long)
    fun save(category: Category): Category
}

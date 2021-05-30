package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository

class FindAllCategories(private val categoryRepository: CategoryRepository) {

    operator fun invoke(): List<Category> {
        return categoryRepository.findAll()
    }

}
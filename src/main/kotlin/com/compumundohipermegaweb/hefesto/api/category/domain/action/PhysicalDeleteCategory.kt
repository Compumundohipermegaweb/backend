package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository

class PhysicalDeleteCategory(private val categoryRepository: CategoryRepository) {

    operator fun invoke(categoryId: Long) {
        categoryRepository.delete(categoryId)
    }
}
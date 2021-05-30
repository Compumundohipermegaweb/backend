package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository

class CreateCategory(private val categoryRepository: CategoryRepository) {
    operator fun invoke(actionData: ActionData): Category {
        val category = Category(0L, actionData.name, actionData.description)
        return categoryRepository.save(category)
    }

    data class ActionData(val name: String, val description: String)
}
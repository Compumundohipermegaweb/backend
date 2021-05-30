package com.compumundohipermegaweb.hefesto.api.category.domain.action

import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository

class UpdateCategory(private val categoryRepository: CategoryRepository) {

    operator fun invoke(actionData: ActionData): Category {
        val category = actionData.toCategory()
        return categoryRepository.save(category)
    }

    private fun ActionData.toCategory() = Category(id, name, description)

    data class ActionData(val id: Long, val name: String, val description: String)
}
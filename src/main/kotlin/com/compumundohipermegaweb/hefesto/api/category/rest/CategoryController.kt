package com.compumundohipermegaweb.hefesto.api.category.rest

import com.compumundohipermegaweb.hefesto.api.category.domain.action.CreateCategory
import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val createCategory: CreateCategory) {

    @PostMapping
    fun createCategory(@RequestBody request: CreateCategoryRequest): ResponseEntity<CreateCategoryResponse> {
        val actionData = CreateCategory.ActionData(request.name, request.description)
        val category = createCategory.invoke(actionData)
        return ResponseEntity.status(HttpStatus.CREATED).body(category.toResponse())
    }
}

private fun Category.toResponse() = CreateCategoryResponse(id, name, description)

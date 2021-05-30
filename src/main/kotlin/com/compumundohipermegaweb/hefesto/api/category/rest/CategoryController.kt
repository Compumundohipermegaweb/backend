package com.compumundohipermegaweb.hefesto.api.category.rest

import com.compumundohipermegaweb.hefesto.api.category.domain.action.CreateCategory
import com.compumundohipermegaweb.hefesto.api.category.domain.action.FindAllCategories
import com.compumundohipermegaweb.hefesto.api.category.domain.action.PhysicalDeleteCategory
import com.compumundohipermegaweb.hefesto.api.category.domain.model.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/api/categories")
class CategoryController(
        private val createCategory: CreateCategory,
        private val findAllCategories: FindAllCategories,
        private val physicalDeleteCategory: PhysicalDeleteCategory) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<FindAllCategoriesResponse> {
        val categories = findAllCategories().map { it.toResponse() }
        return ResponseEntity.ok(FindAllCategoriesResponse(categories))
    }

    @PostMapping
    fun postCategory(@RequestBody request: CreateCategoryRequests): ResponseEntity<CategoryResponse> {
        val actionData = CreateCategory.ActionData(request.name, request.description)
        val category = createCategory(actionData)
        return ResponseEntity.status(HttpStatus.CREATED).body(category.toResponse())
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(@PathVariable("categoryId") categoryId: Long): ResponseEntity<String> {
        physicalDeleteCategory(categoryId)
        return ResponseEntity.noContent().build()
    }
}

private fun Category.toResponse() = CategoryResponse(id, name, description)

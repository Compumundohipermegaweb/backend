package com.compumundohipermegaweb.hefesto.api.category.config

import com.compumundohipermegaweb.hefesto.api.category.domain.action.CreateCategory
import com.compumundohipermegaweb.hefesto.api.category.domain.action.FindAllCategories
import com.compumundohipermegaweb.hefesto.api.category.domain.repository.CategoryRepository
import com.compumundohipermegaweb.hefesto.api.category.infra.repository.CategoryDao
import com.compumundohipermegaweb.hefesto.api.category.infra.repository.JpaCategoryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CategoryConfig {

    @Bean
    fun createCategory(categoryRepository: CategoryRepository): CreateCategory {
        return CreateCategory(categoryRepository)
    }
    
    @Bean
    fun findAllCategories(categoryRepository: CategoryRepository): FindAllCategories {
        return FindAllCategories(categoryRepository)
    }

    @Bean
    fun categoryRepository(categoryDao: CategoryDao): CategoryRepository {
        return JpaCategoryRepository(categoryDao)
    }
}
package com.compumundohipermegaweb.hefesto.api.item.config

import com.compumundohipermegaweb.hefesto.api.item.FindAllItemByShortDescription
import com.compumundohipermegaweb.hefesto.api.item.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.infrastructure.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infrastructure.SpringDataItemRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ItemConfig {
    @Bean
    fun registerItem(jpaItemRepository: JpaItemRepository): RegisterItem {
        return RegisterItem(jpaItemRepository)
    }

    @Bean
    fun findAllItemByShortDescription(jpaItemRepository: JpaItemRepository): FindAllItemByShortDescription {
        return FindAllItemByShortDescription(jpaItemRepository)
    }

    @Bean
    fun jpaItemRepository(springDataItemRepository: SpringDataItemRepository): JpaItemRepository {
        return JpaItemRepository(springDataItemRepository)
    }
}
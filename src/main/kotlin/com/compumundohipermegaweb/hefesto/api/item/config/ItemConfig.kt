package com.compumundohipermegaweb.hefesto.api.item.config

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByShortDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.SpringDataItemRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ItemConfig {
    @Bean
    fun registerItem(itemService: ItemService): RegisterItem {
        return RegisterItem(itemService)
    }

    @Bean
    fun findAllItemByShortDescription(itemService: ItemService): GetItemsByShortDescription {
        return GetItemsByShortDescription(itemService)
    }

    @Bean
    fun itemService(jpaItemRepository: JpaItemRepository): ItemService {
        return DefaultItemService(jpaItemRepository)
    }

    @Bean
    fun jpaItemRepository(springDataItemRepository: SpringDataItemRepository): JpaItemRepository {
        return JpaItemRepository(springDataItemRepository)
    }
}
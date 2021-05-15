package com.compumundohipermegaweb.hefesto.api.item.config

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.SpringDataItemRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ItemConfig {
    @Bean
    fun registerItem(itemService: ItemService, stockService: StockService, supplierRepository: SupplierRepository): RegisterItem {
        return RegisterItem(itemService, stockService, supplierRepository)
    }

    @Bean
    fun findAllItemByShortDescription(itemService: ItemService, stockService: StockService): GetItemsByDescription {
        return GetItemsByDescription(itemService, stockService)
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
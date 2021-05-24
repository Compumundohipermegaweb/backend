package com.compumundohipermegaweb.hefesto.api.item.config

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetAllItems
import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.SpringDataItemRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.SpringDataStock
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ItemConfig {
    @Bean
    fun registerItem(itemService: ItemService, supplierRepository: SupplierRepository): RegisterItem {
        return RegisterItem(itemService, supplierRepository)
    }

    @Bean
    fun findAllItemByShortDescription(itemService: ItemService, stockService: StockService): GetItemsByDescription {
        return GetItemsByDescription(itemService, stockService)
    }

    @Bean
    fun getAllItems(itemService: ItemService): GetAllItems {
        return GetAllItems(itemService)
    }

    @Bean
    fun itemService(jpaItemRepository: JpaItemRepository, stockRepository: StockRepository): ItemService {
        return DefaultItemService(jpaItemRepository, stockRepository)
    }

    @Bean
    fun stockRepository(springDataStock: SpringDataStock): JpaStockRepository {
        return JpaStockRepository(springDataStock)
    }

    @Bean
    fun jpaItemRepository(springDataItemRepository: SpringDataItemRepository): JpaItemRepository {
        return JpaItemRepository(springDataItemRepository)
    }
}
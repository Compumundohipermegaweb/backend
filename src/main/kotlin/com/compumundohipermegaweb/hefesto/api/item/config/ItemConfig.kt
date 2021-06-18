package com.compumundohipermegaweb.hefesto.api.item.config

import com.compumundohipermegaweb.hefesto.api.item.domain.action.*
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.ItemDao
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.StockDao
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ItemConfig {

    @Bean
    fun deleteItem(jpaItemRepository: ItemRepository): DeleteItem {
        return DeleteItem(jpaItemRepository)
    }

    @Bean
    fun registerItem(itemService: ItemService, supplierRepository: SupplierRepository): RegisterItem {
        return RegisterItem(itemService, supplierRepository)
    }

    @Bean
    fun updateItem(itemService: ItemService): UpdateItem {
        return UpdateItem(itemService)
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
    fun stockRepository(stockDao: StockDao): JpaStockRepository {
        return JpaStockRepository(stockDao)
    }

    @Bean
    fun jpaItemRepository(itemDao: ItemDao): JpaItemRepository {
        return JpaItemRepository(itemDao)
    }
}
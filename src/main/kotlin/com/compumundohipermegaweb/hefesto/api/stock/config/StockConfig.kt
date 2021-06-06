package com.compumundohipermegaweb.hefesto.api.stock.config

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.GetAllStockByBranch
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.IncreaseStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.ReduceStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.RegisterStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.DefaultStockService
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.SpringDataStock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StockConfig {

    @Bean
    fun getAllStockByBranch(stockService: StockService, itemService: ItemService): GetAllStockByBranch {
        return GetAllStockByBranch(stockService, itemService)
    }

    @Bean
    fun reduceStock(stockService: StockService): ReduceStock {
        return ReduceStock(stockService)
    }

    @Bean
    fun increaseStock(stockService: StockService): IncreaseStock {
        return IncreaseStock(stockService)
    }

    @Bean
    fun registerStock(stockRepository: StockRepository): RegisterStock {
        return RegisterStock(stockRepository)
    }

    @Bean
    fun stockService(stockRepository: StockRepository, itemRepository: ItemRepository): StockService {
        return DefaultStockService(stockRepository, itemRepository)
    }

    @Bean
    fun stockRepository(springDataStock: SpringDataStock): StockRepository {
        return JpaStockRepository(springDataStock)
    }
}
package com.compumundohipermegaweb.hefesto.api.stock.config

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.GetAllStockByBranch
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
    fun getAllStockByBranch(stockService: StockService): GetAllStockByBranch {
        return GetAllStockByBranch(stockService)
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
package com.compumundohipermegaweb.hefesto.api.stock.config

import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.DefaultStockService
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.StockDao
import com.compumundohipermegaweb.hefesto.api.supplier.domain.service.SupplierService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender


@Configuration
class StockConfig {

    @Bean
    fun restockRiskItems(stockRepository: StockRepository,
                         purchaseOrderRepository: PurchaseOrderRepository,
                         @Autowired mailSender: JavaMailSender,
                         supplierService: SupplierService): RestockRiskItems {
        return RestockRiskItems(stockRepository, purchaseOrderRepository, mailSender, supplierService)
    }

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
    fun stockMinimumAlert(stockRepository: StockRepository, @Autowired emailSender: JavaMailSender, userRepository: UserRepository, itemRepository: ItemRepository): SecurityStockAlert {
        return SecurityStockAlert(stockRepository, emailSender, userRepository, itemRepository)
    }

    @Bean
    fun stockService(stockRepository: StockRepository, itemRepository: ItemRepository): StockService {
        return DefaultStockService(stockRepository, itemRepository)
    }

    @Bean
    fun stockRepository(stockDao: StockDao): StockRepository {
        return JpaStockRepository(stockDao)
    }
}
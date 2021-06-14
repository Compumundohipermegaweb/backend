package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.config

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.ConfirmDispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.DispatchOrders
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action.FindAllDispatches
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.JpaDispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.reposiotory.DispatchDao
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DispatchConfig {

    @Bean
    fun dispatchOrders(dispatchRepository: DispatchRepository,
                       supplierRepository: SupplierRepository,
                       itemRepository: ItemRepository,
                       purchaseOrderRepository: PurchaseOrderRepository,
                       priceToleranceRepository: PriceToleranceRepository): DispatchOrders {
        return DispatchOrders(
                dispatchRepository,
                supplierRepository,
                itemRepository,
                purchaseOrderRepository,
                priceToleranceRepository)
    }

    @Bean
    fun findAllDispatches(dispatchRepository: DispatchRepository,
                          supplierRepository: SupplierRepository,
                          purchaseOrderRepository: PurchaseOrderRepository): FindAllDispatches {
        return FindAllDispatches(dispatchRepository, supplierRepository, purchaseOrderRepository)
    }

    @Bean
    fun confirmDispatch(dispatchRepository: DispatchRepository,
                        purchaseOrderRepository: PurchaseOrderRepository,
                        stockRepository: StockRepository): ConfirmDispatch {
        return ConfirmDispatch(dispatchRepository, purchaseOrderRepository, stockRepository)
    }

    @Bean
    fun dispatchRepository(dispatchDao: DispatchDao): DispatchRepository {
        return JpaDispatchRepository(dispatchDao)
    }
}
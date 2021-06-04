package com.compumundohipermegaweb.hefesto.api.branch.config

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindAllBranches
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.GetStockAvailable
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.JpaBranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.BranchDao
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BranchConfig {
    @Bean
    fun registerBranch (branchRepository: BranchRepository): RegisterBranch{
        return RegisterBranch(branchRepository)
    }

    @Bean
    fun findStockedItems(defaultItemService: ItemService, brandRepository: BrandRepository): FindStockedItems {
        return FindStockedItems(defaultItemService, brandRepository)
    }

    @Bean
    fun getStockAvailable (defaultStockService: StockService): GetStockAvailable {
        return GetStockAvailable(defaultStockService)
    }

    @Bean
    fun findAllBranches(branchRepository: BranchRepository): FindAllBranches {
        return FindAllBranches(branchRepository)
    }

    @Bean
    fun branchRepository(branchCrudRepository: BranchDao): BranchRepository {
        return JpaBranchRepository(branchCrudRepository)
    }

}
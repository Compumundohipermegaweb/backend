package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class GetAllStockByBranch(private val stockService: StockService,
                          private val itemService: ItemService) {
    operator fun invoke(branchId: Long): List<Stock> {
        val stocks = stockService.findAllStockByBranchID(branchId)

        stocks.forEach { findItemDescription(it) }

        return stocks
    }

    private fun findItemDescription(stock: Stock): Stock {
        val item = itemService.findBySku(stock.sku)
        if(item != null){
            stock.itemDescription = item.description
        }
        return stock
    }
}
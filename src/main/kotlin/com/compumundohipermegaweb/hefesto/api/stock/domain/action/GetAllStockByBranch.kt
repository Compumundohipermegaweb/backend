package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class GetAllStockByBranch(private val stockService: StockService) {
    operator fun invoke(branchId: Long): List<Stock> = stockService.findAllStockByBranchID(branchId)
}
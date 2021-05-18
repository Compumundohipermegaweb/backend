package com.compumundohipermegaweb.hefesto.api.branch.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class GetStockAvailable (private val stockService: StockService) {

    operator fun invoke(sku: String, branchId: Long): Stock? {
        return stockService.findBySkuAndBranchId(sku, branchId)
    }

}
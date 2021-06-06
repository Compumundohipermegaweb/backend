package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockRequest

class RegisterStock(private val stockRepository: StockRepository) {
    operator fun invoke(stockRequest: StockRequest) {
        val stock = stockRepository.findBySkuAndBranchId(stockRequest.sku, stockRequest.branchId)
        if(stock == null) {
            stockRepository.save(stockRequest.toStockDao())
        }
    }

    private fun StockRequest.toStockDao(): StockDao {
        return StockDao(0L, sku, branchId, stockTotal, minimumStock, securityStock)
    }
}



package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StocksToReduceRequest

class ReduceStock(private val stockService: StockService) {
    operator fun invoke(toReduce: StocksToReduceRequest, branchId: Long) {
        toReduce.stock.forEach { stockService.reduceStock(it.itemId, branchId, it.amount ) }
    }
}
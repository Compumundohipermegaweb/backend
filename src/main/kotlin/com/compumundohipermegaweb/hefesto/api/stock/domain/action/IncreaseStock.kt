package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockModificationRequest

class IncreaseStock(private val stockService: StockService) {
    operator fun invoke(toIncrease: StockModificationRequest, branchId: Long) {
        toIncrease.stock.forEach { stockService.increaseStock(it.itemId, branchId, it.amount ) }
    }
}
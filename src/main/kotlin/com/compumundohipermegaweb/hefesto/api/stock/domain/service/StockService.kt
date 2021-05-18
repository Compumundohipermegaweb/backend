package com.compumundohipermegaweb.hefesto.api.stock.domain.service

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock

interface StockService {
    fun save(stock: Stock): Stock
    fun findBySku(sku: String): Stock?
    fun reduceStock(idItem: Long, idBranch: Long, amount: Int)
    fun findBySkuAndBranchId(sku: String, branchId: Long): Stock?
}
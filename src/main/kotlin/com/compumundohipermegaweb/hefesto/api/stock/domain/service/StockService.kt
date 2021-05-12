package com.compumundohipermegaweb.hefesto.api.stock.domain.service

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock

interface StockService {
    fun save(stock: Stock): Stock
    fun findBySku(sku: Long): Stock?
}
package com.compumundohipermegaweb.hefesto.api.stock.domain.repository

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import java.util.*

interface StockRepository {
    fun save(stock: StockDao): StockDao
    fun findBySku(sku: String): Optional<StockDao>
    fun findByIdAndBranchId(idItem: Long, branchId: Long): Stock
    fun findAllInStock(branchId: Long): List<Stock>
}
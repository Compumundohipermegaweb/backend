package com.compumundohipermegaweb.hefesto.api.stock.domain.repository

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import java.util.*

interface StockRepository {
    fun save(stock: StockRepresentation): StockRepresentation
    fun findBySku(sku: String): Optional<StockRepresentation>
    fun findByIdAndBranchId(idItem: Long, branchId: Long): Stock?
    fun findAllInStock(branchId: Long): List<Stock>
    fun findBySkuAndBranchId(sku: String, branchId: Long): StockRepresentation?
    fun findLowStock(): List<Stock>
}
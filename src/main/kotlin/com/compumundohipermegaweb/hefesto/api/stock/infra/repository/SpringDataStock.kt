package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import org.springframework.data.repository.CrudRepository
import java.util.*

interface SpringDataStock: CrudRepository<StockDao, Long> {
    fun findBySku(sku: String): Optional<StockDao>
    fun findAllByBranchId(branchId: Long): List<StockDao>
    fun findByIdAndBranchId(id: Long, branchId: Long): StockDao?
    fun findBySkuAndBranchId(sku: String,branchId: Long) : StockDao
}
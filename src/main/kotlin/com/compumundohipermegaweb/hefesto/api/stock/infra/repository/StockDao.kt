package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StockDao: CrudRepository<StockRepresentation, Long> {
    fun findBySku(sku: String): Optional<StockRepresentation>

    fun findAllByBranchId(branchId: Long): List<StockRepresentation>

    fun findByIdAndBranchId(id: Long, branchId: Long): StockRepresentation?

    fun findBySkuAndBranchId(sku: String, branchId: Long): StockRepresentation?

    @Query("select s from StockRepresentation s where s.stockTotal < s.securityStock")
    fun findAllWithLowStock(): List<StockRepresentation>
}
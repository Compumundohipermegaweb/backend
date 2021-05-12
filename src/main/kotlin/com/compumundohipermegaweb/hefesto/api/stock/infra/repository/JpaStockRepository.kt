package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import java.util.*

class JpaStockRepository(private val springDataStock: SpringDataStock): StockRepository {

    override fun save(stock: StockDao): StockDao {
        return springDataStock.save(stock)
    }

    override fun findBySku(sku: Long): Optional<StockDao> {
        return springDataStock.findById(sku)
    }
}





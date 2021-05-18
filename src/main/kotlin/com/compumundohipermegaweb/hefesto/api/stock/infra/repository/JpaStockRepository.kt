package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import java.util.*

class JpaStockRepository(private val springDataStock: SpringDataStock): StockRepository {

    override fun save(stock: StockDao): StockDao {
        return springDataStock.save(stock)
    }

    override fun findBySku(sku: String): Optional<StockDao> {
        return springDataStock.findBySku(sku)
    }

    override fun findByIdAndBranchId(idItem: Long, branchId: Long): Stock? {
        return springDataStock.findByIdAndBranchId(idItem, branchId)?.toStock()
    }

    override fun findBySkuAndBranchId(sku: String, branchId: Long):  StockDao? {
        return springDataStock.findBySkuAndBranchId(sku,branchId)

    }

    override fun findAllInStock(branchId: Long): List<Stock> {
        val stockDao = springDataStock.findAllByBranchId(branchId)

        return stockDao.map{ it.toStock() }
    }

    private fun StockDao.toStock(): Stock {
        return Stock(id, sku, branchId, stockTotal, minimumStock, securityStock)
    }
}





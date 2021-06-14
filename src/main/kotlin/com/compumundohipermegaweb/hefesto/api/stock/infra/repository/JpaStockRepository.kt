package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import java.util.*

class JpaStockRepository(private val stockDao: StockDao): StockRepository {

    override fun save(stock: StockRepresentation): StockRepresentation {
        return stockDao.save(stock)
    }

    override fun findBySku(sku: String): Optional<StockRepresentation> {
        return stockDao.findBySku(sku)
    }

    override fun findByIdAndBranchId(idItem: Long, branchId: Long): Stock? {
        return stockDao.findByIdAndBranchId(idItem, branchId)?.toStock()
    }

    override fun findBySkuAndBranchId(sku: String, branchId: Long):  StockRepresentation? {
        return stockDao.findBySkuAndBranchId(sku,branchId)

    }

    override fun findLowStock(): List<Stock> {
        val representations = stockDao.findAllWithLowStock()
        return representations.map { it.toStock() }
    }

    override fun findAllInStock(branchId: Long): List<Stock> {
        val stockDao = stockDao.findAllByBranchId(branchId)

        return stockDao.map{ it.toStock() }
    }

    private fun StockRepresentation.toStock(): Stock {
        return Stock(id, sku, branchId, stockTotal, minimumStock, securityStock, "")
    }
}





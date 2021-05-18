package com.compumundohipermegaweb.hefesto.api.stock.domain.service

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao

class DefaultStockService(private val stockRepository: StockRepository): StockService {
    override fun save(stock: Stock): Stock {
        return stockRepository.save(stock.toDao()).toStock()
    }

    override fun findBySku(sku: String): Stock? {
        val stock = stockRepository.findBySku(sku)
        if(stock.isPresent) return stock.get().toStock()
        return null
    }

    override fun reduceStock(idItem: Long, idBranch: Long, amount: Int) {
        val stock = stockRepository.findByIdAndBranchId(idItem, idBranch)
        if (stock != null) {
            stock.stockTotal = stock.stockTotal - amount
        }
        if (stock != null) {
            stockRepository.save(stock.toDao()).toStock()
        }
    }

    override fun findBySkuAndBranchId(sku: String, branchId: Long): Stock? {
        val stock = stockRepository.findBySkuAndBranchId(sku,branchId)
        return stock?.toStock()
    }

    private fun Stock.toDao(): StockDao {
        return StockDao(id, sku, branchId, stockTotal, minimumStock, securityStock)
    }

    private fun StockDao.toStock(): Stock {
        return Stock(id, sku, branchId, stockTotal, minimumStock, securityStock)
    }
}
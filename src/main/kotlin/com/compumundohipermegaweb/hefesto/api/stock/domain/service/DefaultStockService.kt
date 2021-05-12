package com.compumundohipermegaweb.hefesto.api.stock.domain.service

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao

class DefaultStockService(private val stockRepository: StockRepository): StockService {
    override fun save(stock: Stock): Stock {
        return stockRepository.save(stock.toDao()).toStock()
    }

    override fun findBySku(sku: Long): Stock? {
        val stock = stockRepository.findBySku(sku)
        if(stock.isPresent) return stock.get().toStock()
        return null
    }

    private fun Stock.toDao(): StockDao {
        return StockDao(id, sku, stockTotal, minimumStock, securityStock, supplier, currency, cost)
    }

    private fun StockDao.toStock(): Stock {
        return Stock(id, sku, stockTotal, minimumStock, securityStock, supplier, currency, cost)
    }
}
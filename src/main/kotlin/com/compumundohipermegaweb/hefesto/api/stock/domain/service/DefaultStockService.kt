package com.compumundohipermegaweb.hefesto.api.stock.domain.service

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation

class DefaultStockService(private val stockRepository: StockRepository,
                          private val itemRepository: ItemRepository): StockService {
    override fun save(stock: Stock): Stock {
        return stockRepository.save(stock.toDao()).toStock()
    }

    override fun findBySku(sku: String): Stock? {
        val stock = stockRepository.findBySku(sku)
        if(stock.isPresent) return stock.get().toStock()
        return null
    }

    override fun reduceStock(idItem: Long, idBranch: Long, amount: Int) {
        val stock: StockRepresentation?
        val sku = itemRepository.findById(idItem)?.sku
        if(sku != null){
            stock = stockRepository.findBySkuAndBranchId(sku, idBranch)
            if (stock != null) {
                stock.stockTotal = stock.stockTotal - amount
                stockRepository.save(stock)
            }
        }
    }

    override fun findBySkuAndBranchId(sku: String, branchId: Long): Stock? {
        val stock = stockRepository.findBySkuAndBranchId(sku,branchId)
        return stock?.toStock()
    }

    override fun findAllStockByBranchID(branchId: Long): List<Stock> {
        return stockRepository.findAllInStock(branchId)
    }

    override fun increaseStock(idItem: Long, idBranch: Long, amount: Int) {
        val stock: StockRepresentation?
        val sku = itemRepository.findById(idItem)?.sku
        if(sku != null){
            stock = stockRepository.findBySkuAndBranchId(sku, idBranch)
            if (stock != null) {
                stock.stockTotal = stock.stockTotal + amount
                stockRepository.save(stock)
            }
        }
    }

    private fun Stock.toDao(): StockRepresentation {
        return StockRepresentation(id, sku, branchId, stockTotal, minimumStock, securityStock)
    }

    private fun StockRepresentation.toStock(): Stock {
        return Stock(id, sku, branchId, stockTotal, minimumStock, securityStock, "")
    }
}
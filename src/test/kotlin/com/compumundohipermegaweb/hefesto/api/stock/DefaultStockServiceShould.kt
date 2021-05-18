package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.DefaultStockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class DefaultStockServiceShould {
    private lateinit var stockRepository: StockRepository
    private lateinit var stockService: DefaultStockService

    private lateinit var stockSaved: Stock
    private  var stockFound: Stock? = null
    private lateinit var stockReduced: Stock

    @Test
    fun `save the stock`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenSavingTheStock()

        thenStockSaved()
    }

    @Test
    fun `find the result for a sku`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenFindingTheStock()

        thenStockFound()
    }

    @Test
    fun `not find the result for a sku non-existent`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenFindingTheStockWhitNonExistsSku()

        thenStockFoundIsNull()
    }

    @Test
    fun `reduce the stock`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenReducingTheStock()

        thenStockReduced()
    }

    private fun givenStockCrudRepository() {
        stockRepository = mock()
        `when`(stockRepository.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(stockRepository.save(STOCK_DAO_SAVED)).thenReturn(STOCK_DAO_SAVED)
        `when`(stockRepository.save(REDUCED_STOCK_DAO)).thenReturn(REDUCED_STOCK_DAO)
        `when`(stockRepository.findBySku("")).thenReturn(Optional.empty())
        `when`(stockRepository.findBySku(STOCK.sku)).thenReturn(Optional.of(STOCK_DAO))
        `when`(stockRepository.findByIdAndBranchId(STOCK.id, STOCK.branchId)).thenReturn(STOCK)
    }

    private fun givenStockRepository() {
        stockService = DefaultStockService(stockRepository)
    }

    private fun whenFindingTheStock() {
        stockFound = stockService.findBySku("1")
    }

    private fun whenFindingTheStockWhitNonExistsSku() {
        stockFound = stockService.findBySku("")
    }

    private fun whenSavingTheStock() {
        stockSaved = stockService.save(STOCK_TO_SAVE)
    }

    private fun whenReducingTheStock() {
        stockReduced = stockService.reduceStock(0L, 0, 50)
    }

    private fun thenStockFound() {
        then(stockFound).isNotNull
    }

    private fun thenStockFoundIsNull() {
        then(stockFound).isNull()
    }

    private fun thenStockSaved() {
        verify(stockRepository).save(STOCK_DAO_SAVED)
        then(stockSaved).isNotNull
    }

    private fun thenStockReduced(){
        verify(stockRepository).save(REDUCED_STOCK_DAO)
        then(STOCK.stockTotal).isEqualTo(50)
    }

    private companion object {
        val STOCK_TO_SAVE = Stock(2L, "2", 0, 0, 0,0)
        val STOCK_DAO_SAVED = StockDao(2L, "2", 0, 0,0, 0)
        val STOCK = Stock(0L, "1", 0, 100, 0,0)
        val STOCK_DAO = StockDao(0L, "1", 0, 100,0, 0)
        val REDUCED_STOCK_DAO = StockDao(0L, "1", 0, 50,0, 0)
    }
}
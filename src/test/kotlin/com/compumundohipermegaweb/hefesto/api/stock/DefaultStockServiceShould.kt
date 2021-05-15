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

    private fun givenStockCrudRepository() {
        stockRepository = mock()
        `when`(stockRepository.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(stockRepository.findBySku("")).thenReturn(Optional.empty())
        `when`(stockRepository.findBySku("1")).thenReturn(Optional.of(STOCK_DAO))
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
        stockSaved = stockService.save(STOCK)
    }

    private fun thenStockFound() {
        then(stockFound).isNotNull
    }

    private fun thenStockFoundIsNull() {
        then(stockFound).isNull()
    }

    private fun thenStockSaved() {
        verify(stockRepository).save(STOCK_DAO)
        then(stockSaved).isNotNull
    }

    private companion object {
        val STOCK = Stock(0L, "1", 0, 0, 0,0)
        val STOCK_DAO = StockDao(0L, "1", 0, 0,0, 0)
    }
}
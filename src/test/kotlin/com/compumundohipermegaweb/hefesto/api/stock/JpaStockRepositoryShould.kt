package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.SpringDataStock
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class JpaStockRepositoryShould {
    private lateinit var springDataStock: SpringDataStock
    private lateinit var stockRepository: StockRepository

    private lateinit var stockSaved: StockDao
    private lateinit var stockFound: Optional<StockDao>

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

        thenStockFoundIsEmpty()
    }

    private fun givenStockCrudRepository() {
        springDataStock = mock(SpringDataStock::class.java)
        `when`(springDataStock.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(springDataStock.findById(0L)).thenReturn(Optional.empty())
        `when`(springDataStock.findById(1L)).thenReturn(Optional.of(STOCK_DAO))
    }

    private fun givenStockRepository() {
        stockRepository = JpaStockRepository(springDataStock)
    }

    private fun whenFindingTheStock() {
        stockFound = stockRepository.findBySku(1L)
    }

    private fun whenFindingTheStockWhitNonExistsSku() {
        stockFound = stockRepository.findBySku(0L)
    }

    private fun whenSavingTheStock() {
        stockSaved = stockRepository.save(STOCK_DAO)
    }

    private fun thenStockFound() {
        then(stockFound.get()).isNotNull
    }

    private fun thenStockFoundIsEmpty() {
        then(stockFound).isNotPresent
    }

    private fun thenStockSaved() {
        verify(springDataStock).save(STOCK_DAO)
        then(stockSaved).isNotNull
    }

    private companion object {
        val STOCK_DAO = StockDao(0L, 1L, 0, 0, 0,0)
    }
}
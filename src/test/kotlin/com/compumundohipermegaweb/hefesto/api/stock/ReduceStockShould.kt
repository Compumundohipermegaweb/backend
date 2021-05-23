package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.ReduceStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockToModifyRequest
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockModificationRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class ReduceStockShould {
    private lateinit var stockService: StockService
    private lateinit var reduceStock: ReduceStock

    @Test
    fun `reduce all the stocks`() {
        givenStockService()
        givenReduceStock()

        whenReducingTheStock()

        thenTheStockIsReduced()
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.reduceStock(1L, 1L, 100)).then {  }
        `when`(stockService.reduceStock(2L, 1L, 100)).then {  }
        `when`(stockService.reduceStock(3L, 1L, 100)).then {  }
    }

    private fun givenReduceStock() {
        reduceStock = ReduceStock(stockService)
    }

    private fun whenReducingTheStock() {
        reduceStock.invoke(TO_REDUCE, 1L)
    }

    private fun thenTheStockIsReduced() {
        verify(stockService).reduceStock(1L, 1L, 100)
        verify(stockService).reduceStock(2L, 1L, 100)
        verify(stockService).reduceStock(3L, 1L, 100)
    }

    private companion object {

        private val TO_REDUCE = StockModificationRequest(listOf(
            StockToModifyRequest(1L, 100),
            StockToModifyRequest(2L, 100),
            StockToModifyRequest(3L, 100)))

    }
}
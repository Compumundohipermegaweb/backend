package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.IncreaseStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockModificationRequest
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockToModifyRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IncreaseStockShould {
    private lateinit var stockService: StockService
    private lateinit var increaseStock: IncreaseStock

    @Test
    fun `increase all the stocks`() {
        givenStockService()
        givenReduceStock()

        whenIncreasingTheStock()

        thenTheStockIsIncreased()
    }

    private fun givenStockService() {
        stockService = mock()
        Mockito.`when`(stockService.increaseStock(1L, 1L, 100)).then {  }
        Mockito.`when`(stockService.increaseStock(2L, 1L, 100)).then {  }
        Mockito.`when`(stockService.increaseStock(3L, 1L, 100)).then {  }
    }

    private fun givenReduceStock() {
        increaseStock = IncreaseStock(stockService)
    }

    private fun whenIncreasingTheStock() {
        increaseStock.invoke(TO_REDUCE, 1L)
    }

    private fun thenTheStockIsIncreased() {
        verify(stockService).increaseStock(1L, 1L, 100)
        verify(stockService).increaseStock(2L, 1L, 100)
        verify(stockService).increaseStock(3L, 1L, 100)
    }

    private companion object {

        private val TO_REDUCE = StockModificationRequest(listOf(
            StockToModifyRequest(1L, 100),
            StockToModifyRequest(2L, 100),
            StockToModifyRequest(3L, 100)
        ))

    }
}
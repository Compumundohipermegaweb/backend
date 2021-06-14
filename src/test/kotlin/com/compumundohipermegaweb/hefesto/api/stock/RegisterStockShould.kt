package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.RegisterStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class RegisterStockShould {
    private lateinit var stockRepository: StockRepository

    private lateinit var registerStock: RegisterStock

    @Test
    fun `register stock`() {
        givenStockRepository()
        givenRegisterStock()

        whenRegisterTheStock(STOCK_REQUEST)

        thenTheStockIsRegistered()
    }

    @Test
    fun `not register stock`() {
        givenStockRepository()
        givenRegisterStock()

        whenRegisterTheStock(ANOTHER_STOCK_REQUEST)

        thenTheStockIsNotRegistered()
    }


    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.findBySkuAndBranchId("SKU1", 0)).thenReturn(null)
        `when`(stockRepository.findBySkuAndBranchId("SKU2", 1)).thenReturn(ANOTHER_STOCK_DAO)
        `when`(stockRepository.save(STOCK_DAO)).thenReturn(STOCK_DAO)
    }

    private fun givenRegisterStock() {
        registerStock = RegisterStock(stockRepository)
    }

    private fun whenRegisterTheStock(stockRequest: StockRequest) {
        registerStock.invoke(stockRequest)
    }

    private fun thenTheStockIsRegistered() {
        verify(stockRepository).findBySkuAndBranchId("SKU1", 0)
        verify(stockRepository).save(STOCK_DAO)
    }

    private fun thenTheStockIsNotRegistered() {
        verify(stockRepository).findBySkuAndBranchId("SKU2", 1)
        verify(stockRepository, times(0)).save(ANOTHER_STOCK_DAO)
    }

    private companion object {
        private val STOCK_REQUEST = StockRequest("SKU1", 0, 0, 0, 0)
        private val STOCK_DAO = StockRepresentation(0L,"SKU1", 0, 0, 0, 0)

        private val ANOTHER_STOCK_REQUEST = StockRequest("SKU2", 1, 0, 0, 0)
        private val ANOTHER_STOCK_DAO = StockRepresentation(1L,"SKU2", 1, 0, 0, 0)
    }
}
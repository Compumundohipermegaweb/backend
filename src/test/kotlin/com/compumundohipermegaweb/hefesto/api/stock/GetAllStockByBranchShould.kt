package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.GetAllStockByBranch
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllStockByBranchShould {

    private lateinit var stockService: StockService
    private lateinit var getAllStockByBranch: GetAllStockByBranch

    private lateinit var stocked: List<Stock>

    @Test
    fun `find the stock of a given branch`() {
        givenStockService()
        givenGetAllStockByBranch()

        whenFindingStockOfABranch()

        thenStockWasFound()
    }

    @Test
    fun `return the stock found of a given branch`() {
        givenStockService()
        givenGetAllStockByBranch()

        whenFindingStockOfABranch()

        thenStockFoundWasReturned()
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.findAllStockByBranchID(10L)).thenReturn(STOCK)
    }

    private fun givenGetAllStockByBranch() {
        getAllStockByBranch = GetAllStockByBranch(stockService)
    }

    private fun whenFindingStockOfABranch() {
        stocked = getAllStockByBranch.invoke(10L)
    }

    private fun thenStockWasFound() {
        verify(stockService).findAllStockByBranchID(10L)
        then(stocked).isNotEmpty
    }

    private fun thenStockFoundWasReturned() {
        then(stocked).isEqualTo(STOCK)
    }

    private companion object {
        val STOCK = listOf(
            Stock(1L, "1", 10L, 10, 5, 1),
            Stock(2L, "2", 10L, 10, 5, 1),
            Stock(3L, "3", 10L, 10, 5, 1),
            Stock(4L, "4", 10L, 10, 5, 1)
        )
    }

}
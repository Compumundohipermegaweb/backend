package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
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
    private lateinit var itemService: ItemService
    private lateinit var getAllStockByBranch: GetAllStockByBranch

    private lateinit var stocked: List<Stock>

    @Test
    fun `find the stock of a given branch`() {
        givenStockService()
        givenItemService()
        givenGetAllStockByBranch()

        whenFindingStockOfABranch()

        thenStockWasFound()
    }

    @Test
    fun `return the stock found of a given branch`() {
        givenStockService()
        givenItemService()
        givenGetAllStockByBranch()

        whenFindingStockOfABranch()

        thenStockFoundWasReturned()
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.findAllStockByBranchID(10L)).thenReturn(STOCK)
    }

    private fun givenItemService() {
        itemService = mock()
        `when`(itemService.findBySku("1")).thenReturn(ITEM_UNO)
        `when`(itemService.findBySku("2")).thenReturn(ITEM_DOS)
        `when`(itemService.findBySku("3")).thenReturn(ITEM_TRES)
        `when`(itemService.findBySku("4")).thenReturn(ITEM_CUATRO)
    }

    private fun givenGetAllStockByBranch() {
        getAllStockByBranch = GetAllStockByBranch(stockService, itemService)
    }

    private fun whenFindingStockOfABranch() {
        stocked = getAllStockByBranch.invoke(10L)
    }

    private fun thenStockWasFound() {
        verify(stockService).findAllStockByBranchID(10L)
        then(stocked).isNotEmpty
    }

    private fun thenStockFoundWasReturned() {
        then(stocked).isEqualTo(EXPECTED_STOCK)
    }

    private companion object {
        val STOCK = listOf(
            Stock(1L, "1", 10L, 10, 5, 1, ""),
            Stock(2L, "2", 10L, 10, 5, 1, ""),
            Stock(3L, "3", 10L, 10, 5, 1, ""),
            Stock(4L, "4", 10L, 10, 5, 1, "")
        )

        val EXPECTED_STOCK = listOf(
            Stock(1L, "1", 10L, 10, 5, 1, "TEST1"),
            Stock(2L, "2", 10L, 10, 5, 1, "TEST2"),
            Stock(3L, "3", 10L, 10, 5, 1, "TEST3"),
            Stock(4L, "4", 10L, 10, 5, 1, "TEST4")
        )

        val ITEM_UNO = Item(1L, "1", "", "TEST1", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_DOS = Item(2L, "2", "", "TEST2", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_TRES = Item(3L, "3", "", "TEST3", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_CUATRO = Item(4L, "4", "", "TEST4", 1L, 1L, "", 1.0, 10.0, false, "", 0)
    }

}
package com.compumundohipermegaweb.hefesto.api.branch


import com.compumundohipermegaweb.hefesto.api.branch.domain.action.GetStockAvailable
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`


class GetStockAvailableShould {
    private lateinit var stockService: StockService
    private lateinit var getStockAvailable :GetStockAvailable
    private lateinit var stockAvailable : Stock

    @Test
    fun `get stock available by branch and sku`(){

        givenStockService()
        givenStockAvailableBySkuAndBranch()
        whenSearchingStockAvailable()
        thenAvailableStockFound()
    }
    private fun givenStockAvailableBySkuAndBranch() {
        getStockAvailable = GetStockAvailable(stockService)
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.findBySkuAndBranchId(SKU,BRANCH_ID)).thenReturn(STOCK)
    }

    private fun whenSearchingStockAvailable() {
        stockAvailable = getStockAvailable.invoke(SKU, BRANCH_ID)!!
    }

    private fun thenAvailableStockFound() {
        then(stockAvailable.stockTotal).isGreaterThan(0)
    }


    companion object{
        const val BRANCH_ID = 1L
        const val SKU="1"
        val STOCK = Stock(0L, "1", 1L, 3, 0,0, "")
    }
}
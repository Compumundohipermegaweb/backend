package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.DefaultStockService
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class DefaultStockServiceShould {
    private lateinit var stockRepository: StockRepository
    private lateinit var itemRepository: ItemRepository
    private lateinit var stockService: DefaultStockService

    private lateinit var stockSaved: Stock
    private  var stockFound: Stock? = null
    private lateinit var stocked: List<Stock>

    @Test
    fun `save the stock`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenSavingTheStock()

        thenStockSaved()
    }

    @Test
    fun `find the result for a sku`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenFindingTheStock()

        thenStockFound()
    }

    @Test
    fun `not find the result for a sku non-existent`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenFindingTheStockWhitNonExistsSku()

        thenStockFoundIsNull()
    }
    @Test
    fun `find the result for a sku and branch`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()
        whenFindingTheStockAvailableBySkuAndBranch()
        thenStockAvailableFound()
    }

    @Test
    fun `reduce the stock`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenReducingTheStock()

        thenStockReduced()
    }

    @Test
    fun `find the stock of a given branch`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenFindingStockOfABranch()

        thenStockWasFound()
    }

    @Test
    fun `return the stock found of a given branch`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenFindingStockOfABranch()

        thenStockFoundWasReturned()
    }

    @Test
    fun `increase the stock`() {
        givenStockRepository()
        givenItemRepository()
        givenStockService()

        whenIncreasingTheStock()

        thenStockIncreased()
    }

    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(stockRepository.save(STOCK_DAO_SAVED)).thenReturn(STOCK_DAO_SAVED)
        `when`(stockRepository.save(REDUCED_STOCK_DAO)).thenReturn(REDUCED_STOCK_DAO)
        `when`(stockRepository.findBySku("")).thenReturn(Optional.empty())
        `when`(stockRepository.findBySku(STOCK.sku)).thenReturn(Optional.of(STOCK_DAO))
        `when`(stockRepository.findByIdAndBranchId(STOCK.id, STOCK.branchId)).thenReturn(STOCK)
        `when`(stockRepository.findBySkuAndBranchId("1",1L)).thenReturn(STOCK_DAO_1)
        `when`(stockRepository.findBySkuAndBranchId("2",0L)).thenReturn(STOCK_DAO)
        `when`(stockRepository.findAllInStock(0)).thenReturn(listOf(STOCK, STOCK_TO_SAVE))

        `when`(stockRepository.findBySkuAndBranchId("5",5L)).thenReturn(STOCK_DAO_TO_INCREASE)
        `when`(stockRepository.save(STOCK_DAO_INCREASED)).thenReturn(STOCK_DAO_INCREASED)

    }

    private fun givenItemRepository() {
        itemRepository = mock()
        `when`(itemRepository.findById(0L)).thenReturn(ITEM)
        `when`(itemRepository.findById(5L)).thenReturn(ITEM_TO_INCREASE_STOCK)
    }

    private fun givenStockService() {
        stockService = DefaultStockService(stockRepository, itemRepository)
    }

    private fun whenFindingTheStock() {
        stockFound = stockService.findBySku("1")
    }

    private fun whenFindingTheStockWhitNonExistsSku() {
        stockFound = stockService.findBySku("")
    }
    private fun whenFindingTheStockAvailableBySkuAndBranch() {
        stockFound = stockService.findBySkuAndBranchId("1",1L)
    }

    private fun whenSavingTheStock() {
        stockSaved = stockService.save(STOCK_TO_SAVE)
    }

    private fun whenReducingTheStock() {
        stockService.reduceStock(0L, 0, 50)
    }

    private fun whenFindingStockOfABranch() {
        stocked = stockService.findAllStockByBranchID(0L)
    }

    private fun whenIncreasingTheStock() {
        stockService.increaseStock(5L, 5L, 50)
    }

    private fun thenStockFound() {
        then(stockFound).isNotNull
    }
    private fun thenStockAvailableFound() {
        then(stockFound!!.stockTotal).isGreaterThan(0)
    }

    private fun thenStockFoundIsNull() {
        then(stockFound).isNull()
    }

    private fun thenStockSaved() {
        verify(stockRepository).save(STOCK_DAO_SAVED)
        then(stockSaved).isNotNull
    }

    private fun thenStockReduced(){
        verify(stockRepository).save(STOCK_DAO)
        then(STOCK.stockTotal).isEqualTo(50)
    }

    private fun thenStockWasFound(){
        verify(stockRepository).findAllInStock(0L)
        then(stocked).isNotNull
    }

    private fun thenStockFoundWasReturned(){
        then(stocked).isEqualTo(listOf(STOCK, STOCK_TO_SAVE))
    }

    private fun thenStockIncreased(){
        verify(stockRepository).save(STOCK_DAO_INCREASED)
        then(STOCK_DAO_INCREASED.stockTotal).isEqualTo(150)
    }

    private companion object {
        val ITEM = Item(0L, "2", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val STOCK_TO_SAVE = Stock(2L, "2", 0, 0, 0,0, "")
        val STOCK_DAO_SAVED = StockRepresentation(2L, "2", 0, 0,0, 0)
        val STOCK = Stock(0L, "1", 0, 50, 0,0, "")
        val STOCK_DAO = StockRepresentation(0L, "2", 0, 100,0, 0)
        val REDUCED_STOCK_DAO = StockRepresentation(1L, "1", 1, 50,0, 0)
        val STOCK_DAO_1 = StockRepresentation(0L, "1", 1L, 3,0, 0)

        val ITEM_TO_INCREASE_STOCK = Item(0L, "5", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val STOCK_DAO_TO_INCREASE = StockRepresentation(5L, "5", 5L, 100,0, 0)
        val STOCK_DAO_INCREASED = StockRepresentation(5L, "5", 5L, 150,0, 0)
    }
}
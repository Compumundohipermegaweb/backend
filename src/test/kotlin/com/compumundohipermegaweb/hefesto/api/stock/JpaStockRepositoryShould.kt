package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.StockDao
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockRepresentation
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class JpaStockRepositoryShould {
    private lateinit var stockDao: StockDao
    private lateinit var stockRepository: StockRepository

    private lateinit var stockSaved: StockRepresentation
    private lateinit var stockFound: Optional<StockRepresentation>
    private lateinit var stockFoundForIdItem: Stock
    private lateinit var stocked: List<Stock>

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

    @Test
    fun `find the stock of a given branch`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenFindingStockOfABranch()

        thenStockWasFound(EXPECTED_STOCK[0], EXPECTED_STOCK[1], EXPECTED_STOCK[2], EXPECTED_STOCK[3])
    }

    @Test
    fun `find the result for a id item`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenFindingTheStockForIdItem()

        thenStockFoundForId()
    }

    @Test
    fun `find items with low stock`() {
        givenStockCrudRepository()
        givenStockRepository()

        whenFindingLowStock()

        thenLowStockHasBeenFound()
    }

     @Test
     fun `increase the stock of a given sku`() {
         givenStockCrudRepository()
         givenStockRepository()

         whenIncreasingStockBySku()

         thenStockIsIncreased()
     }

    private fun givenStockCrudRepository() {
        stockDao = mock()
        `when`(stockDao.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(stockDao.findBySku("")).thenReturn(Optional.empty())
        `when`(stockDao.findBySku("1")).thenReturn(Optional.of(STOCK_DAO))
        `when`(stockDao.findBySku(STOCK_REPRESENTATION.sku)).thenReturn(Optional.of(STOCK_REPRESENTATION))
        `when`(stockDao.findAllByBranchId(BRANCH_ID)).thenReturn(SAVED_STOCK)
        `when`(stockDao.findByIdAndBranchId(0L, 0)).thenReturn(STOCK_DAO)
    }

    private fun givenStockRepository() {
        stockRepository = JpaStockRepository(stockDao)
    }

    private fun whenFindingTheStock() {
        stockFound = stockRepository.findBySku("1")
    }

    private fun whenFindingTheStockForIdItem() {
        stockFoundForIdItem = stockRepository.findByIdAndBranchId(0L, 0)!!
    }

    private fun whenFindingTheStockWhitNonExistsSku() {
        stockFound = stockRepository.findBySku("")
    }

    private fun whenSavingTheStock() {
        stockSaved = stockRepository.save(STOCK_DAO)
    }

    private fun whenFindingStockOfABranch() {
        stocked = stockRepository.findAllInStock(BRANCH_ID)
    }

    private fun whenFindingLowStock() {
        stocked = stockRepository.findLowStock()
    }

    private fun whenIncreasingStockBySku() {
        stockRepository.increaseStock(STOCK_REPRESENTATION.sku, 1)
    }

    private fun thenStockFound() {
        then(stockFound.get()).isNotNull
    }

    private fun thenStockFoundForId() {
        then(stockFoundForIdItem).isNotNull
        then(stockFoundForIdItem).isEqualTo(STOCK)
    }

    private fun thenStockFoundIsEmpty() {
        then(stockFound).isNotPresent
    }

    private fun thenStockSaved() {
        verify(stockDao).save(STOCK_DAO)
        then(stockSaved).isNotNull
    }

    private fun thenStockWasFound(vararg expected: Stock) {
        then(stocked).containsOnly(*expected)
    }

    private fun thenLowStockHasBeenFound() {
        verify(stockDao).findAllWithLowStock()
    }

    private fun thenStockIsIncreased() {
        verify(stockDao).updateStockBySku(STOCK_REPRESENTATION.sku, STOCK_REPRESENTATION.stockTotal + 1)
    }

    private companion object {
        const val BRANCH_ID = 100L
        val STOCK_DAO = StockRepresentation(0L, "1", 0, 0, 0,0)
        val STOCK = Stock(0L, "1", 0, 0, 0,0, "")

        val SAVED_STOCK = listOf(
                StockRepresentation(1L, "1", BRANCH_ID, 10, 5, 1),
                StockRepresentation(2L, "2", BRANCH_ID, 10, 5, 1),
                StockRepresentation(3L, "3", BRANCH_ID, 10, 5, 1),
                StockRepresentation(4L, "4", BRANCH_ID, 10, 5, 1)
        )

        val EXPECTED_STOCK = listOf(
                Stock(1L, "1", BRANCH_ID, 10, 5, 1, ""),
                Stock(2L, "2", BRANCH_ID, 10, 5, 1, ""),
                Stock(3L, "3", BRANCH_ID, 10, 5, 1, ""),
                Stock(4L, "4", BRANCH_ID, 10, 5, 1, "")
        )

        val STOCK_REPRESENTATION = StockRepresentation(1L, "111", 1L, 20, 10, 30)
    }
}
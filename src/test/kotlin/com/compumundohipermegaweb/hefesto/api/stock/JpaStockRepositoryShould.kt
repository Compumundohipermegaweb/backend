package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.JpaStockRepository
import com.compumundohipermegaweb.hefesto.api.stock.infra.repository.SpringDataStock
import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class JpaStockRepositoryShould {
    private lateinit var springDataStock: SpringDataStock
    private lateinit var stockRepository: StockRepository

    private lateinit var stockSaved: StockDao
    private lateinit var stockFound: Optional<StockDao>
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

    private fun givenStockCrudRepository() {
        springDataStock = mock()
        `when`(springDataStock.save(STOCK_DAO)).thenReturn(STOCK_DAO)
        `when`(springDataStock.findBySku("")).thenReturn(Optional.empty())
        `when`(springDataStock.findBySku("1")).thenReturn(Optional.of(STOCK_DAO))
        `when`(springDataStock.findAllByBranchId(BRANCH_ID)).thenReturn(SAVED_STOCK)
        `when`(springDataStock.findByIdAndBranchId(0L, 0)).thenReturn(STOCK_DAO)
    }

    private fun givenStockRepository() {
        stockRepository = JpaStockRepository(springDataStock)
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
        verify(springDataStock).save(STOCK_DAO)
        then(stockSaved).isNotNull
    }

    private fun thenStockWasFound(vararg expected: Stock) {
        then(stocked).containsOnly(*expected)
    }

    private companion object {
        const val BRANCH_ID = 100L
        val STOCK_DAO = StockDao(0L, "1", 0, 0, 0,0)
        val STOCK = Stock(0L, "1", 0, 0, 0,0, "")

        val SAVED_STOCK = listOf(
                StockDao(1L, "1", BRANCH_ID, 10, 5, 1),
                StockDao(2L, "2", BRANCH_ID, 10, 5, 1),
                StockDao(3L, "3", BRANCH_ID, 10, 5, 1),
                StockDao(4L, "4", BRANCH_ID, 10, 5, 1)
        )

        val EXPECTED_STOCK = listOf(
                Stock(1L, "1", BRANCH_ID, 10, 5, 1, ""),
                Stock(2L, "2", BRANCH_ID, 10, 5, 1, ""),
                Stock(3L, "3", BRANCH_ID, 10, 5, 1, ""),
                Stock(4L, "4", BRANCH_ID, 10, 5, 1, "")
        )
    }
}
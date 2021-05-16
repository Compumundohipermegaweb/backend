package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultItemServiceShould {
    private lateinit var itemRepository: ItemRepository
    private lateinit var stockRepository: StockRepository
    private lateinit var itemService: DefaultItemService

    private lateinit var savedItem: Item
    private lateinit var itemFound: Item
    private lateinit var itemsFound: List<Item>

    @Test
    fun `return the saved item`() {
        givenItemRepository()
        givenStockRepository()
        givenItemService()

        whenSavingTheItem()

        thenItemIsSaved()

    }

    @Test
    fun `save the item`() {
        givenItemRepository()
        givenStockRepository()
        givenItemService()

        whenSavingTheItem()

        thenItemIsReturned()
    }

    @Test
    fun `find the item`() {
        givenItemRepository()
        givenStockRepository()
        givenItemService()

        whenSearchingTheItem()

        thenItemIsFound()
    }

    @Test
    fun `return de found item`() {
        givenItemRepository()
        givenStockRepository()
        givenItemService()

        whenSearchingTheItem()

        thenItemFoundIsReturned()
    }

    @Test
    fun `find the stock of a branch`() {
        givenItemRepository()
        givenStockRepository()
        givenItemService()

        whenFindingStock()

        thenTheAllItemsInStockWhereFound()
    }

    private fun givenItemRepository() {
        itemRepository = mock()
        `when`(itemRepository.save(ITEM_DAO)).thenReturn(ITEM_DAO)
        `when`(itemRepository.findAllItemByShortDescription(SHORT_DESCRIPTION)).thenReturn(listOf(ITEM_DAO))

        `when`(itemRepository.findBySku(STOCK[0].sku)).thenReturn(ITEMS[0])
        `when`(itemRepository.findBySku(STOCK[1].sku)).thenReturn(ITEMS[1])
        `when`(itemRepository.findBySku(STOCK[2].sku)).thenReturn(ITEMS[2])
        `when`(itemRepository.findBySku(STOCK[3].sku)).thenReturn(ITEMS[3])
    }

    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.findAllInStock(BRANCH_ID)).thenReturn(STOCK)
    }

    private fun givenItemService() {
        itemService = DefaultItemService(itemRepository, stockRepository)
    }

    private fun whenSavingTheItem() {
        savedItem = itemService.save(ITEM)
    }

    private fun whenSearchingTheItem() {
        itemFound = itemService.findAllItemByShortDescription(SHORT_DESCRIPTION)[0]
    }

    private fun whenFindingStock() {
        itemsFound = itemService.findAllWithStock(BRANCH_ID)
    }

    private fun thenItemIsReturned() {
        then(savedItem).isEqualTo(ITEM)
    }

    private fun thenItemIsSaved() {
        verify(itemRepository).save(ITEM_DAO)
    }

    private fun thenItemIsFound() {
        verify(itemRepository).findAllItemByShortDescription(SHORT_DESCRIPTION)
    }

    private fun thenItemFoundIsReturned() {
        then(itemFound).isEqualTo(ITEM)
    }

    private fun thenTheAllItemsInStockWhereFound() {
        then(itemsFound).containsAll(EXPECTED_ITEMS)
    }

    private companion object {
        const val SHORT_DESCRIPTION = "SHORT DESCRIPTION"
        private val ITEM = Item(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "", 0)
        private val ITEM_DAO = ItemDao(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "")
        const val BRANCH_ID = 10L
        val STOCK = listOf(
                Stock(0L, "0", BRANCH_ID, 99, 10, 15),
                Stock(1L, "1", BRANCH_ID, 6, 3, 5),
                Stock(2L, "2", BRANCH_ID, 100, 20, 50),
                Stock(3L, "3", BRANCH_ID, 30, 10, 15)
        )

        val ITEMS = listOf(
                Item(0L, "0", "", "", 1L, 1L, "", 1.0, false, "", 0),
                Item(1L, "1", "", "", 1L, 1L, "", 1.0, false, "", 0),
                Item(2L, "2", "", "", 1L, 1L, "", 1.0, false, "", 0),
                Item(3L, "3", "", "", 1L, 1L, "", 1.0, false, "", 0)
        )

        val EXPECTED_ITEMS = listOf(
                Item(0L, "0", "", "", 1L, 1L, "", 1.0, false, "", 99),
                Item(1L, "1", "", "", 1L, 1L, "", 1.0, false, "", 6),
                Item(2L, "2", "", "", 1L, 1L, "", 1.0, false, "", 100),
                Item(3L, "3", "", "", 1L, 1L, "", 1.0, false, "", 30)
        )

    }
}
package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetItemsByDescriptionShould {
    private lateinit var getItemsByDescription: GetItemsByDescription
    private lateinit var itemService: ItemService
    private lateinit var stockService: StockService
    private lateinit var foundItems: List<Item>
    private lateinit var foundItem: Item

    @Test
    fun `find items by short description`(){
        givenItemService()
        givenStockService()
        givenItemFinderByShortDescrption()

        whenSearchingItem()

        thenTheItemIsSuccessfullyFound()
    }

    @Test
    fun `return the found items by short description`(){
        givenItemService()
        givenStockService()
        givenItemFinderByShortDescrption()

        whenSearchingItem()

        thenTheItemsFoundAreSuccessfullyReturned()
    }

    private fun givenItemService() {
        itemService = mock(ItemService::class.java)
        `when`(itemService.findAllItemByShortDescription(ITEM.shortDescription)).thenReturn(listOf(ITEM))
    }

    private fun givenStockService() {
        stockService = mock(StockService::class.java)
        `when`(stockService.findBySku("")).thenReturn(STOCK)
    }

    private fun givenItemFinderByShortDescrption() {
        getItemsByDescription = GetItemsByDescription(itemService, stockService)
    }

    private fun whenSearchingItem() {
        foundItems = getItemsByDescription.invoke(SHORT_DESCRIPTION)
        foundItem = foundItems[0]
    }

    private fun thenTheItemIsSuccessfullyFound() {
       verify(itemService).findAllItemByShortDescription(SHORT_DESCRIPTION)
    }

    private fun thenTheItemsFoundAreSuccessfullyReturned() {
        then(foundItems).isNotEmpty
        then(foundItem).isEqualTo(ITEM)
    }

    private companion object {
        const val SHORT_DESCRIPTION = "short description"
        private val ITEM = Item(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "", 0)
        val STOCK = Stock(0L, "1", 0, 0, 0,0)
    }
}
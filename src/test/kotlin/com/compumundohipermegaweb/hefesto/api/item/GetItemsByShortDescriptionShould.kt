package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByShortDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetItemsByShortDescriptionShould {
    private lateinit var getItemsByShortDescription: GetItemsByShortDescription
    private lateinit var itemService: ItemService
    private lateinit var foundItems: List<Item>
    private lateinit var foundItem: Item

    @Test
    fun `find items by short description`(){
        givenItemService()
        givenItemFinderByShortDescrption()

        whenSearchingItem()

        thenTheItemIsSuccessfullyFound()
    }

    @Test
    fun `return the found items by short description`(){
        givenItemService()
        givenItemFinderByShortDescrption()

        whenSearchingItem()

        thenTheItemsFoundAreSuccessfullyReturned()
    }

    private fun givenItemService() {
        itemService = mock(ItemService::class.java)
        `when`(itemService.findAllItemByShortDescription(ITEM.shortDescription)).thenReturn(listOf(ITEM))
    }

    private fun givenItemFinderByShortDescrption() {
        getItemsByShortDescription = GetItemsByShortDescription(itemService)
    }

    private fun whenSearchingItem() {
        foundItems = getItemsByShortDescription.invoke(SHORT_DESCRIPTION)
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
        private val ITEM = Item(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "")
    }
}
package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetAllItems
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllItemsShould {
    private lateinit var itemService: ItemService
    private lateinit var getAllItems: GetAllItems

    private lateinit var allItem: List<Item>

    @Test
    fun `find all items`() {
        givenItemService()
        givenGetAllItems()

        whenFindingAllItems()

        thenAllItemsFound()
    }

    @Test
    fun `return all items`() {
        givenItemService()
        givenGetAllItems()

        whenFindingAllItems()

        thenAllItemsFoundReturned()
    }

    private fun givenItemService() {
        itemService = mock()
        `when`(itemService.findAllItems()).thenReturn(ALL_ITEMS)
    }

    private fun givenGetAllItems() {
        getAllItems = GetAllItems(itemService)
    }

    private fun whenFindingAllItems() {
        allItem = getAllItems.invoke()
    }

    private fun thenAllItemsFound() {
        verify(itemService).findAllItems()
        then(allItem).isNotNull
    }

    private fun thenAllItemsFoundReturned() {
        then(allItem).isEqualTo(ALL_ITEMS)
    }

    private companion object {
        val ALL_ITEMS = listOf(
            Item(0L, "0", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0),
            Item(1L, "1", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0),
            Item(2L, "2", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0),
            Item(3L, "3", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        )
    }
}
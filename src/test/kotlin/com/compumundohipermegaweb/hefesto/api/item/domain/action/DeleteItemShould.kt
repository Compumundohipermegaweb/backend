package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class DeleteItemShould {

    private lateinit var itemRepository: ItemRepository

    private lateinit var deleteItem: DeleteItem

    @Test
    fun `delete the item by sku`() {
        givenItemRepository()
        givenDeleteItem()

        whenDeletingItem()

        thenTheItemHasBeenDeleted()
    }

    private fun givenItemRepository() {
        itemRepository = mock()
    }

    private fun givenDeleteItem() {
        deleteItem = DeleteItem(itemRepository)
    }

    private fun thenTheItemHasBeenDeleted() {
        verify(itemRepository).deleteBySku(SKU)
    }

    private fun whenDeletingItem() {
        deleteItem(SKU)
    }

    private companion object {
        const val SKU = "ITEM_SKU"
    }
}
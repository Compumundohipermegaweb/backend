package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DefaultItemServiceShould {
    private lateinit var itemRepository: ItemRepository
    private lateinit var itemService: DefaultItemService
    private lateinit var savedItem: Item
    private lateinit var itemFound: Item

    @Test
    fun `return the saved item`() {
        givenItemRepository()
        givenItemService()

        whenSavingTheItem()

        thenItemIsSaved()

    }

    @Test
    fun `save the item`() {
        givenItemRepository()
        givenItemService()

        whenSavingTheItem()

        thenItemIsReturned()
    }

    @Test
    fun `find the item`() {
        givenItemRepository()
        givenItemService()

        whenSearchingTheItem()

        thenItemIsFound()
    }

    @Test
    fun `return de found item`() {
        givenItemRepository()
        givenItemService()

        whenSearchingTheItem()

        thenItemFoundIsReturned()
    }

    private fun givenItemRepository() {
        itemRepository = mock(ItemRepository::class.java)
        `when`(itemRepository.save(ITEM_DAO)).thenReturn(ITEM_DAO)
        `when`(itemRepository.findAllItemByShortDescription(SHORT_DESCRIPTION)).thenReturn(listOf(ITEM_DAO))
    }

    private fun givenItemService() {
        itemService = DefaultItemService(itemRepository)
    }

    private fun whenSavingTheItem() {
        savedItem = itemService.save(ITEM)
    }

    private fun whenSearchingTheItem() {
        itemFound = itemService.findAllItemByShortDescription(SHORT_DESCRIPTION)[0]
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

    private companion object {
        const val SHORT_DESCRIPTION = "SHORT DESCRIPTION"
        private val ITEM = Item(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "", 0)
        private val ITEM_DAO = ItemDao(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "")

    }
}
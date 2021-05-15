package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.SpringDataItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaItemRepositoryShould {
    private lateinit var springDataItemRepository: SpringDataItemRepository
    private lateinit var itemRepository: ItemRepository
    private lateinit var savedItem: ItemDao
    private lateinit var itemsFound: List<ItemDao>

    @Test
    fun `save the item`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenSavingTheItem()

        thenItemSaved()
    }

    @Test
    fun `return the saved item`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenSavingTheItem()

        thenTheItemSavedIsReturned()
    }

    @Test
    fun `find the item`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenSearchingTheItem()

        thenItemsFound()
    }

    @Test
    fun `return the found item`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenSearchingTheItem()

        thenTheItemsFoundIsReturned()
    }


    private fun givenItemCrudRepository() {
        springDataItemRepository = mock(SpringDataItemRepository::class.java)
        `when`(springDataItemRepository.save(ITEM_DAO)).thenReturn(ITEM_DAO)
        `when`(springDataItemRepository.findAllItemByDescription("%$DESCRIPTION%")).thenReturn(listOf(ITEM_DAO, ANOTHER_ITEM_DAO))
    }

    private fun givenItemRepository() {
        itemRepository = JpaItemRepository(springDataItemRepository)
    }

    private fun whenSavingTheItem() {
        savedItem = itemRepository.save(ITEM_DAO)
    }

    private fun whenSearchingTheItem() {
        itemsFound = itemRepository.findAllItemByShortDescription(DESCRIPTION)
    }

    private fun thenItemSaved() {
        verify(springDataItemRepository).save(ITEM_DAO)
    }

    private fun thenTheItemSavedIsReturned() {
        then(savedItem).isEqualTo(ITEM_DAO)
    }

    private fun thenItemsFound() {
        verify(springDataItemRepository).findAllItemByDescription("%$DESCRIPTION%")
    }

    private fun thenTheItemsFoundIsReturned() {
        then(itemsFound[0]).isEqualTo(ITEM_DAO)
        then(itemsFound[1]).isEqualTo(ANOTHER_ITEM_DAO)
    }

    private companion object {
        const val SHORT_DESCRIPTION = "SHORT DESCRIPTION"
        const val DESCRIPTION = "DESCRIPTION"
        private val ITEM_DAO = ItemDao(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, true, "")
        private val ANOTHER_ITEM_DAO = ItemDao(0L, "", DESCRIPTION, "", 0L, 0L, "", 0.0, true, "")

    }
}
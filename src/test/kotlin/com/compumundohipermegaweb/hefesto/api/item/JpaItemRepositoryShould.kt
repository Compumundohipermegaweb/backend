package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.SpringDataItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class JpaItemRepositoryShould {
    private lateinit var springDataItemRepository: SpringDataItemRepository
    private lateinit var itemRepository: ItemRepository
    private lateinit var savedItem: ItemRepresentation
    private lateinit var itemsFound: List<ItemRepresentation>
    private lateinit var allItems: List<Item>
    private var itemFound: Item? = null

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
    fun `find the item by description`() {
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

    @Test
    fun `find by sku`() {
        givenItemCrudRepository()
        givenItemRepository()

        itemFound = itemRepository.findBySku(SKU)

        then(itemFound).isEqualTo(EXPECTED_ITEM)
    }

    @Test
    fun `find by id`() {
        givenItemCrudRepository()
        givenItemRepository()

        itemFound = itemRepository.findById(1L)

        then(itemFound).isEqualTo(EXPECTED_ITEM)
    }

    @Test
    fun `not find by id`() {
        givenItemCrudRepository()
        givenItemRepository()

        itemFound = itemRepository.findById(0L)

        then(itemFound).isNull()
    }

    @Test
    fun `find all items`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenFindingAllItems()

        thenAllItemsFound()
    }

    @Test
    fun `return all items`() {
        givenItemCrudRepository()
        givenItemRepository()

        whenFindingAllItems()

        thenAllItemsFoundReturned()
    }

    private fun givenItemCrudRepository() {
        springDataItemRepository = mock(SpringDataItemRepository::class.java)
        `when`(springDataItemRepository.save(ITEM_DAO)).thenReturn(ITEM_DAO)
        `when`(springDataItemRepository.findAllItemByDescription("%$DESCRIPTION%")).thenReturn(listOf(ITEM_DAO, ANOTHER_ITEM_DAO))
        `when`(springDataItemRepository.findBySku(SKU)).thenReturn(ITEM_DAO_WITH_SKU)
        `when`(springDataItemRepository.findById(ID)).thenReturn(Optional.of(ITEM_DAO_WITH_SKU))
        `when`(springDataItemRepository.findById(0L)).thenReturn(Optional.empty())
        `when`(springDataItemRepository.findAll()).thenReturn(listOf(ITEM_DAO, ITEM_DAO_WITH_SKU, ANOTHER_ITEM_DAO))
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

    private fun whenFindingAllItems() {
        allItems = itemRepository.findAllItem()
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

    private fun thenAllItemsFound() {
        verify(springDataItemRepository).findAll()
        then(allItems).isNotNull
    }

    private fun thenAllItemsFoundReturned() {
        verify(springDataItemRepository).findAll()
        then(allItems).isEqualTo(listOf(ITEM, ITEM_WITH_SKU ,ANOTHER))
    }

    private companion object {
        const val ID = 1L
        const val SKU = "123"
        const val SHORT_DESCRIPTION = "SHORT DESCRIPTION"
        const val DESCRIPTION = "DESCRIPTION"
        val ITEM_DAO = ItemRepresentation(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, 10.0, true, "")
        val ANOTHER_ITEM_DAO = ItemRepresentation(0L, "", DESCRIPTION, "", 0L, 0L, "", 0.0, 10.0, true, "")
        val ITEM_DAO_WITH_SKU = ItemRepresentation(1L, SKU, "", "", 1L, 1L, "", 11.0, 10.0, false, "")

        val ITEM = Item(0L, "", SHORT_DESCRIPTION, "", 0L, 0L, "", 0.0, 10.0, true, "", 0)
        val ANOTHER = Item(0L, "", DESCRIPTION, "", 0L, 0L, "", 0.0, 10.0, true, "", 0)
        val ITEM_WITH_SKU = Item(1L, SKU, "", "", 1L, 1L, "", 11.0, 10.0, false, "", 0)

        val EXPECTED_ITEM = Item(1L, SKU, "", "", 1L, 1L, "", 11.0, 10.0, false, "", 0)
    }
}
package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.JpaItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.repository.ItemDao
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class JpaItemRepositoryShould {
    private lateinit var itemDao: ItemDao
    private lateinit var itemRepository: ItemRepository
    private lateinit var savedItem: ItemRepresentation
    private lateinit var itemsFound: List<ItemRepresentation>
    private lateinit var allItems: List<Item>
    private var itemFound: Item? = null

    @Test
    fun `save the item`() {
        givenItemDao()
        givenItemRepository()

        whenSavingTheItem()

        thenItemSaved()
    }

    @Test
    fun `return the saved item`() {
        givenItemDao()
        givenItemRepository()

        whenSavingTheItem()

        thenTheItemSavedIsReturned()
    }

    @Test
    fun `find the item by description`() {
        givenItemDao()
        givenItemRepository()

        whenSearchingTheItem()

        thenItemsFound()
    }

    @Test
    fun `return the found item`() {
        givenItemDao()
        givenItemRepository()

        whenSearchingTheItem()

        thenTheItemsFoundIsReturned()
    }

    @Test
    fun `find by sku`() {
        givenItemDao()
        givenItemRepository()

        itemFound = itemRepository.findBySku(SKU)

        then(itemFound).isEqualTo(EXPECTED_ITEM)
    }

    @Test
    fun `find by id`() {
        givenItemDao()
        givenItemRepository()

        itemFound = itemRepository.findById(1L)

        then(itemFound).isEqualTo(EXPECTED_ITEM)
    }

    @Test
    fun `not find by id`() {
        givenItemDao()
        givenItemRepository()

        itemFound = itemRepository.findById(0L)

        then(itemFound).isNull()
    }

    @Test
    fun `find all items`() {
        givenItemDao()
        givenItemRepository()

        whenFindingAllItems()

        thenAllItemsFound()
    }

    @Test
    fun `return all items`() {
        givenItemDao()
        givenItemRepository()

        whenFindingAllItems()

        thenAllItemsFoundReturned()
    }

    @Test
    fun `delete an item by its sku`() {
        givenItemDao()
        givenItemRepository()

        itemRepository.deleteBySku("SKU")

        verify(itemDao).deleteBySku("SKU")
    }

    private fun givenItemDao() {
        itemDao = mock(ItemDao::class.java)
        `when`(itemDao.save(ITEM_DAO)).thenReturn(ITEM_DAO)
        `when`(itemDao.findAllItemByDescription("%$DESCRIPTION%")).thenReturn(listOf(ITEM_DAO, ANOTHER_ITEM_DAO))
        `when`(itemDao.findBySku(SKU)).thenReturn(ITEM_DAO_WITH_SKU)
        `when`(itemDao.findById(ID)).thenReturn(Optional.of(ITEM_DAO_WITH_SKU))
        `when`(itemDao.findById(0L)).thenReturn(Optional.empty())
        `when`(itemDao.findAll()).thenReturn(listOf(ITEM_DAO, ITEM_DAO_WITH_SKU, ANOTHER_ITEM_DAO))
    }

    private fun givenItemRepository() {
        itemRepository = JpaItemRepository(itemDao)
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
        verify(itemDao).save(ITEM_DAO)
    }

    private fun thenTheItemSavedIsReturned() {
        then(savedItem).isEqualTo(ITEM_DAO)
    }

    private fun thenItemsFound() {
        verify(itemDao).findAllItemByDescription("%$DESCRIPTION%")
    }

    private fun thenTheItemsFoundIsReturned() {
        then(itemsFound[0]).isEqualTo(ITEM_DAO)
        then(itemsFound[1]).isEqualTo(ANOTHER_ITEM_DAO)
    }

    private fun thenAllItemsFound() {
        verify(itemDao).findAll()
        then(allItems).isNotNull
    }

    private fun thenAllItemsFoundReturned() {
        verify(itemDao).findAll()
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
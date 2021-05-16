package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.FindItems
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class FindItemsShould {

    private lateinit var itemRepository: ItemRepository
    private lateinit var findItems: FindItems

    private lateinit var itemsFound: List<Item>

    @Test
    fun `filter items by category`() {
        givenItemRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_CATEGORY_ONLY)

        then(itemsFound).containsOnly(ITEMS[0])
    }

    @Test
    fun `filter by description`() {
        givenItemRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_DESCRIPTION_ONLY)

        then(itemsFound).containsOnly(ITEMS[1])
    }

    @Test
    fun `filter by brand`() {
        givenItemRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).containsOnly(ITEMS[1], ITEMS[2])
    }

    @Test
    fun `filter by imported`() {
        givenItemRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_IMPORTED_ONLY)

        then(itemsFound).containsOnly(ITEMS[2], ITEMS[3])
    }

    @Test
    fun `filter by multiple fields`() {
        givenItemRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_AND_IMPORTED)

        then(itemsFound).containsOnly(ITEMS[2], ITEMS[3])
    }

    private fun givenItemRepository() {
        itemRepository = mock()
        `when`(itemRepository.findAll()).thenReturn(ITEMS)
    }

    private fun givenFindItems() {
        findItems = FindItems(itemRepository)
    }

    private fun whenFindingItems(with: SearchCriteria) {
        itemsFound = findItems(with)
    }

    private companion object {
        val SEARCH_BY_CATEGORY_ONLY = SearchCriteria(10L, null, null, null)
        val SEARCH_BY_DESCRIPTION_ONLY = SearchCriteria(null, "b", null, null)
        val SEARCH_BY_BRAND_ONLY = SearchCriteria(null, null, 30L, null)
        val SEARCH_BY_IMPORTED_ONLY = SearchCriteria(null, null, null, true)
        val SEARCH_BY_BRAND_AND_IMPORTED = SearchCriteria(20L, null, null, true)
        val ITEMS = listOf(
                Item(1L, "1", "", "a", 1L, 10L, "", 0.0, false, "", 0),
                Item(2L, "2", "", "b", 30L, 20L, "", 0.0, false, "", 0),
                Item(3L, "3", "", "c", 30L, 20L, "", 0.0, true, "", 0),
                Item(4L, "4", "", "asd", 60L, 20L, "", 0.0, true, "", 0)
        )
    }
}
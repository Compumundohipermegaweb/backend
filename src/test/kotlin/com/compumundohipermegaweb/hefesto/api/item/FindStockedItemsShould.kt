package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class FindStockedItemsShould {

    private lateinit var itemService: ItemService
    private lateinit var findStockedItems: FindStockedItems

    private lateinit var itemsFound: List<Item>

    @Test
    fun `filter items by category`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_CATEGORY_ONLY)

        then(itemsFound).containsOnly(ITEMS[0])
    }

    @Test
    fun `filter by description`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_DESCRIPTION_ONLY)

        then(itemsFound).containsOnly(ITEMS[1])
    }

    @Test
    fun `filter by brand`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).containsOnly(ITEMS[1], ITEMS[2])
    }

    @Test
    fun `filter by imported`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_IMPORTED_ONLY)

        then(itemsFound).containsOnly(ITEMS[2], ITEMS[3])
    }

    @Test
    fun `filter by multiple fields`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_AND_IMPORTED)

        then(itemsFound).containsOnly(ITEMS[2], ITEMS[3])
    }

    @Test
    fun `filter items without stock`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_AND_IMPORTED)

        verify(itemService).findAllWithStock(BRANCH_ID)
    }

    @Test
    fun `search items of a given branch`() {
        givenItemService()
        givenBranchHasItems(branch = BRANCH_ID, items = ITEMS)
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).isNotNull
    }

    private fun givenItemService() {
        itemService = mock()
    }

    private fun givenBranchHasItems(branch: Long, items: List<Item>) {
        `when`(itemService.findAllWithStock(branch)).thenReturn(items)
    }

    private fun givenFindItems() {
        findStockedItems = FindStockedItems(itemService)
    }

    private fun whenFindingItems(with: SearchCriteria) {
        itemsFound = findStockedItems(with)
    }

    private companion object {
        const val BRANCH_ID = 14L
        val SEARCH_BY_CATEGORY_ONLY = SearchCriteria(BRANCH_ID, 10L, null, null, null)
        val SEARCH_BY_DESCRIPTION_ONLY = SearchCriteria(BRANCH_ID, null, "b", null, null)
        val SEARCH_BY_BRAND_ONLY = SearchCriteria(BRANCH_ID, null, null, 30L, null)
        val SEARCH_BY_IMPORTED_ONLY = SearchCriteria(BRANCH_ID, null, null, null, true)
        val SEARCH_BY_BRAND_AND_IMPORTED = SearchCriteria(BRANCH_ID, 20L, null, null, true)
        val ITEMS = listOf(
                Item(1L, "1", "", "a", 1L, 10L, "", 0.0, false, "", 22),
                Item(2L, "2", "", "b", 30L, 20L, "", 0.0, false, "", 1),
                Item(3L, "3", "", "c", 30L, 20L, "", 0.0, true, "", 999),
                Item(4L, "4", "", "asd", 60L, 20L, "", 0.0, true, "", 14)
        )
    }
}
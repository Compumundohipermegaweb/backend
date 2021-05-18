package com.compumundohipermegaweb.hefesto.api.branch

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemStock
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class FindStockedItemsShould {

    private lateinit var itemService: ItemService
    private lateinit var brandRepository: BrandRepository
    private lateinit var findStockedItems: FindStockedItems

    private lateinit var itemsFound: List<ItemStock>

    @Test
    fun `filter items by category`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_CATEGORY_ONLY)

        then(itemsFound).containsOnly(EXPECTED_ITEMS[0])
    }

    @Test
    fun `filter by description`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_DESCRIPTION_ONLY)

        then(itemsFound).containsOnly(EXPECTED_ITEMS[1])
    }

    @Test
    fun `filter by brand`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).containsOnly(EXPECTED_ITEMS[2], EXPECTED_ITEMS[3])
    }

    @Test
    fun `filter by imported`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_IMPORTED_ONLY)

        then(itemsFound).containsOnly(EXPECTED_ITEMS[2], EXPECTED_ITEMS[3])
    }

    @Test
    fun `filter by multiple fields`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_CATEGORY_AND_IMPORTED)

        then(itemsFound).containsOnly(EXPECTED_ITEMS[2], EXPECTED_ITEMS[3])
    }

    @Test
    fun `filter items without stock`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_CATEGORY_AND_IMPORTED)

        verify(itemService).findAllWithStock(BRANCH_ID)
    }

    @Test
    fun `search items of a given branch`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).isNotNull
    }

    @Test
    fun `retrieve the brand name`() {
        givenItemService()
        givenBrandRepository()
        givenFindItems()

        whenFindingItems(SEARCH_BY_BRAND_ONLY)

        then(itemsFound).allMatch { it.brandName != "" }
    }

    private fun givenItemService() {
        itemService = mock()
        `when`(itemService.findAllWithStock(BRANCH_ID)).thenReturn(SAVED_ITEMS)
    }

    private fun givenBrandRepository() {
        brandRepository = mock()
        `when`(brandRepository.findById(BRAND_1.id)).thenReturn(BRAND_1)
        `when`(brandRepository.findById(BRAND_2.id)).thenReturn(BRAND_2)
    }

    private fun givenFindItems() {
        findStockedItems = FindStockedItems(itemService, brandRepository)
    }

    private fun whenFindingItems(with: SearchCriteria) {
        itemsFound = findStockedItems(with)
    }

    private companion object {
        const val BRANCH_ID = 14L
        val BRAND_1 = Brand(1L, "brand1")
        val BRAND_2 = Brand(2L, "brand2")
        val SEARCH_BY_CATEGORY_ONLY = SearchCriteria(BRANCH_ID, 10L, null, null, null)
        val SEARCH_BY_DESCRIPTION_ONLY = SearchCriteria(BRANCH_ID, null, "b", null, null)
        val SEARCH_BY_BRAND_ONLY = SearchCriteria(BRANCH_ID, null, null, BRAND_2.id, null)
        val SEARCH_BY_IMPORTED_ONLY = SearchCriteria(BRANCH_ID, null, null, null, true)
        val SEARCH_BY_CATEGORY_AND_IMPORTED = SearchCriteria(BRANCH_ID, 20L, null, null, true)

        val SAVED_ITEMS = listOf(
                Item(1L, "1", "", "a", BRAND_1.id, 10L, "", 10.0, false, "", 20),
                Item(2L, "2", "", "b", BRAND_1.id, 1L, "", 20.0, false, "", 100),
                Item(3L, "3", "", "c", BRAND_2.id, 20L, "", 20.0, true, "", 69),
                Item(4L, "4", "", "asd", BRAND_2.id, 20L, "", 20.0, true, "", 14),
        )

        val EXPECTED_ITEMS = listOf(
                ItemStock(1L, "1", "", "a", BRAND_1.name, 10.0, 20,false, 10L),
                ItemStock(2L, "2", "", "b", BRAND_1.name, 20.0, 100,false, 1L),
                ItemStock(3L, "3", "", "c", BRAND_2.name, 20.0, 69,true, 20L),
                ItemStock(4L, "4", "", "asd", BRAND_2.name, 20.0, 14,true, 20L)
        )
    }
}
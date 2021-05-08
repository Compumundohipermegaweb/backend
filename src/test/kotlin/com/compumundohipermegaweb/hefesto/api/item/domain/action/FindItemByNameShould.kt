package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class FindItemByNameShould {
    private lateinit var findItemByName: FindAllItemByShortDescription
    private lateinit var itemRepository: ItemRepository
    private lateinit var foundItem: List<Item>

    @Test
    fun `register a invoice`(){
        givenItemRepository()
        givenFinderByNameItem()

        whenSearchingItem(ITEM.shortDescription)

        thenTheItemIsSuccessfullyFound()
    }

    private fun givenItemRepository() {
        itemRepository = Mockito.mock(ItemRepository::class.java)
        `when`(itemRepository.findAllItemByShortDescription(ITEM.shortDescription)).thenReturn(listOf(ITEM))
    }

    private fun givenFinderByNameItem() {
        findItemByName = FindAllItemByShortDescription(itemRepository)
    }

    private fun whenSearchingItem(name: String) {
        foundItem = findItemByName(name)
    }

    private fun thenTheItemIsSuccessfullyFound() {
       then(foundItem).isNotEmpty
    }

    private companion object {
        private val ITEM = Item(0L, "sku","short description", "long description", "measure", 0, 0,  0, "supplier", 0.0, 0.0)
    }
}
package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RegisterItemShould {

    private lateinit var registerItem: RegisterItem;
    private lateinit var itemRepository: ItemRepository
    private lateinit var registeredItem: Item

    @Test
    fun `register a invoice`(){
        givenItemRepository()
        givenRegisterItem()

        whenRegisteringItem(ITEM)

        thenTheItemIsSuccessfullyRegister()
    }

    private fun givenItemRepository() {
        itemRepository = Mockito.mock(ItemRepository::class.java)
        `when`(itemRepository.save(ITEM)).thenReturn(ITEM)
    }

    private fun givenRegisterItem() {
       registerItem = RegisterItem(itemRepository)
    }

    private fun whenRegisteringItem(item: Item) {
        registeredItem = registerItem(item)
    }

    private fun thenTheItemIsSuccessfullyRegister() {
        then(registerItem).isNotNull
    }

    private companion object {
        private val ITEM = Item(0L, "SKU", "short description", "long description", "measure", 0, 0, 0, "supplier", 0.0, 0.0)
    }
}
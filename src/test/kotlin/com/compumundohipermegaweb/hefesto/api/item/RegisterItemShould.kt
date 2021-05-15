package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.DefaultItemService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RegisterItemShould {

    private lateinit var registerItem: RegisterItem;
    private lateinit var itemService: ItemService
    private lateinit var registeredItem: Item

    @Test
    fun `register a item`(){
        givenItemRepository()
        givenRegisterItem()

        whenRegisteringItem()

        thenTheItemIsSuccessfullyRegister()
    }

    @Test
    fun `return registered item`(){
        givenItemRepository()
        givenRegisterItem()

        whenRegisteringItem()

        thenTheItemRegisteredIsSuccessfullyReturned()
    }

    private fun givenItemRepository() {
        itemService = mock(ItemService::class.java)
        `when`(itemService.save(ITEM)).thenReturn(ITEM)
    }

    private fun givenRegisterItem() {
       registerItem = RegisterItem(itemService)
    }

    private fun whenRegisteringItem() {
        registeredItem = registerItem(ITEM_REQUEST)
    }

    private fun thenTheItemIsSuccessfullyRegister() {
        verify(itemService).save(ITEM)
    }

    private fun thenTheItemRegisteredIsSuccessfullyReturned() {
        then(registerItem).isNotNull
    }

    private companion object {
        private val ITEM = Item(0L, "", "", "", 0L, 0L, "", 0.0, true, "")
        private val ITEM_REQUEST = ItemRequest("", "", "",  0L, 0L, "", 0.0, true, "")
    }
}
package com.compumundohipermegaweb.hefesto.api.item


import com.compumundohipermegaweb.hefesto.api.item.domain.action.UpdateItem
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test


class UpdateItemShould {

    private lateinit var updateItem: UpdateItem
    private lateinit var itemService: ItemService

    @Test
    fun `update a item`(){

        itemService = mock()
        updateItem = UpdateItem(itemService)
        updateItem.invoke(ITEM_REQUEST)
        then(updateItem).isNotNull

    }

    private companion object {
        private val ITEM_REQUEST = ItemRequest(0L,"", "Desc", "",  0L, 0L, "", 0.0, 10.0, true, "",null)
    }
}
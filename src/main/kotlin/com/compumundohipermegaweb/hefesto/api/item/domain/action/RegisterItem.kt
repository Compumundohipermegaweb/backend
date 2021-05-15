package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest

class RegisterItem(private val itemService: ItemService) {

    operator fun invoke(item: ItemRequest) = itemService.save(item.toItem())

    private fun ItemRequest.toItem(): Item {
        return Item(0L, sku, shortDescription, description, brandId, categoryId, uomSale, price, imported, state)
    }
}
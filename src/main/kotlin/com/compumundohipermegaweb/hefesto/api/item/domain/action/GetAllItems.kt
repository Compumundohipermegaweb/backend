package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService

class GetAllItems(private val itemService: ItemService) {
    operator fun invoke(): List<Item> = itemService.findAllItems()
}
package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService

class GetItemsByShortDescription (private val itemService: ItemService) {
    operator fun invoke(shortDescription: String) = itemService.findAllItemByShortDescription(shortDescription)
}
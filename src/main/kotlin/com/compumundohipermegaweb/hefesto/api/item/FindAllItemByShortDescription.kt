package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemRepository

class FindAllItemByShortDescription (private val itemRepository: ItemRepository) {
    operator fun invoke(name: String) = itemRepository.findAllItemByShortDescription(name)
}
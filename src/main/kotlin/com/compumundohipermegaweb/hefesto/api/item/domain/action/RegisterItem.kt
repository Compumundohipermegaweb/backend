package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemRepository

class RegisterItem(private val itemRepository: ItemRepository) {
    operator fun invoke(item: Item) = itemRepository.save(item)
}
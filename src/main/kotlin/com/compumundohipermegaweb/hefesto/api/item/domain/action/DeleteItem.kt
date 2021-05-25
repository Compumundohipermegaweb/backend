package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository

class DeleteItem(private val itemRepository: ItemRepository) {

    operator fun invoke(sku: String) {
        itemRepository.deleteBySku(sku)
    }
}
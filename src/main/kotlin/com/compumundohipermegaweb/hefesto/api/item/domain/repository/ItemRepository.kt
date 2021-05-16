package com.compumundohipermegaweb.hefesto.api.item.domain.repository

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao

interface ItemRepository {
    fun save(item: ItemDao): ItemDao
    fun findAllItemByShortDescription(description: String): List<ItemDao>
    fun findAllWithStock(): List<Item>
}
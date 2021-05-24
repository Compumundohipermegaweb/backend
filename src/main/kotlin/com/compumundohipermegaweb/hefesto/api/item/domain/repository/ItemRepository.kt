package com.compumundohipermegaweb.hefesto.api.item.domain.repository

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation

interface ItemRepository {
    fun save(item: ItemRepresentation): ItemRepresentation
    fun findAllItemByShortDescription(description: String): List<ItemRepresentation>
    fun findBySku(sku: String): Item?
    fun findById(id: Long): Item?
    fun findAllItem(): List<Item>
}
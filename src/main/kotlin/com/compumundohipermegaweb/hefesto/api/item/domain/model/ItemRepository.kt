package com.compumundohipermegaweb.hefesto.api.item.domain.model

interface ItemRepository {
    fun save(item: Item): Item
    fun findAllItemByShortDescription(shortDescription: String): List<Item>
}
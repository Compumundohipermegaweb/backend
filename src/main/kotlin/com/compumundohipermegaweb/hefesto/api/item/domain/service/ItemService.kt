package com.compumundohipermegaweb.hefesto.api.item.domain.service

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item

interface ItemService {
    fun save(item: Item): Item
    fun findAllItemByShortDescription(shortDescription: String): List<Item>
    fun findAllWithStock(branchId: Long): List<Item>
    fun findItemById(id: Long): Item?
    fun findAllItems(): List<Item>
    fun findBySku(sku: String): Item?
}
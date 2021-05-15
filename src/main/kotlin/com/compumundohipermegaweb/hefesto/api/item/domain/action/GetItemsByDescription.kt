package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class GetItemsByDescription (private val itemService: ItemService,
                             private val stockService: StockService) {

    operator fun invoke(shortDescription: String): List<Item> {
        val items = itemService.findAllItemByShortDescription(shortDescription)
        items.stream().map { it.availableStock = findAvailableStock(it.sku) }
        return items
    }

    private fun findAvailableStock(sku: String): Int {
        return stockService.findBySku(sku)?.stockTotal ?: 0
    }
}
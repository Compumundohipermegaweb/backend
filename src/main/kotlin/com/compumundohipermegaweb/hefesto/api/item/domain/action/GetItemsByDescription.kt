package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class GetItemsByDescription (private val itemService: ItemService,
                             private val stockService: StockService) {

    operator fun invoke(shortDescription: String): List<Item> {
        val items = itemService.findAllItemByShortDescription(shortDescription)
        findAvailableStock(items)
        return items
    }

    private fun findAvailableStock(items: List<Item>) {
        for(item in items){
            val stock = stockService.findBySku(item.sku)
            if (stock != null) {
                item.availableStock = stock.stockTotal
            } else {
                item.availableStock = 0
            }
        }
    }
}
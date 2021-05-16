package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService

class FindStockedItems(private val itemService: ItemService) {
    operator fun invoke(searchCriteria: SearchCriteria): List<Item> {
        var items = itemService.findAllWithStock(searchCriteria.branch)

        if(searchCriteria.category != null) {
            items = items.filter { it.categoryId == searchCriteria.category }
        }

        if (!searchCriteria.description.isNullOrBlank()) {
            items = items.filter { it.description.contains(searchCriteria.description) }
        }

        if (searchCriteria.brand != null) {
            items = items.filter { it.brandId == searchCriteria.brand }
        }

        if (searchCriteria.imported != null) {
            items = items.filter { it.imported == searchCriteria.imported }
        }

        return items
    }
}
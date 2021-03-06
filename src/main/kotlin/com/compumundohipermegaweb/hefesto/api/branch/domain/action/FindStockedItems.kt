package com.compumundohipermegaweb.hefesto.api.branch.domain.action

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemStock
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService

class FindStockedItems(private val itemService: ItemService, private val brandRepository: BrandRepository) {
    operator fun invoke(searchCriteria: SearchCriteria): List<ItemStock> {
        var items = itemService.findAllWithStock(searchCriteria.branch)

        if(searchCriteria.category != null) {
            items = items.filter { it.categoryId == searchCriteria.category }
        }

        if (!searchCriteria.description.isNullOrBlank()) {
            items = items.filter { it.description.toLowerCase().contains(searchCriteria.description.toLowerCase()) }
        }

        if (searchCriteria.brand != null) {
            items = items.filter { it.brandId == searchCriteria.brand }
        }

        if (searchCriteria.imported != null) {
            items = items.filter { it.imported == searchCriteria.imported }
        }

        return items.map {
            val brand = brandRepository.findById(it.brandId)

            ItemStock(
                    id = it.id,
                    sku = it.sku,
                    shortDescription = it.shortDescription,
                    longDescription = it.description,
                    brandName = brand?.name ?: DEFAULT_BRAND_NAME,
                    price = it.price,
                    availableStock = it.availableStock,
                    imported = it.imported,
                    category = it.categoryId)
        }
    }

    private companion object {
        const val DEFAULT_BRAND_NAME = "Generico"
    }
}
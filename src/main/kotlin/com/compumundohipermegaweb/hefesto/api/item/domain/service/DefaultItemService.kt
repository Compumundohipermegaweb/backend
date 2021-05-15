package com.compumundohipermegaweb.hefesto.api.item.domain.service

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao
import kotlin.streams.toList

class DefaultItemService(private val itemRepository: ItemRepository): ItemService {

    override fun save(item: Item): Item {
        return itemRepository.save(item.toItemDao()).toItem()
    }

    override fun findAllItemByShortDescription(shortDescription: String): List<Item> {
        return itemRepository.findAllItemByShortDescription(shortDescription).stream().map { it.toItem() }.toList()
    }

    private fun Item.toItemDao(): ItemDao {
        return ItemDao(id, sku.toUpperCase(), shortDescription.toUpperCase(), description.toUpperCase(), brandId, categoryId, uomSale.toUpperCase(), price, imported, state.toUpperCase())
    }

    private fun ItemDao.toItem(): Item {
        return Item(id, sku, shortDescription, description, brandId, categoryId, uomSale, price, imported, state, 0)
    }
}



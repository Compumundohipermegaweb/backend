package com.compumundohipermegaweb.hefesto.api.item.domain.service

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import kotlin.streams.toList

class DefaultItemService(private val itemRepository: ItemRepository,
                         private val stockRepository: StockRepository): ItemService {

    override fun save(item: Item): Item {
        return itemRepository.save(item.toItemDao()).toItem()
    }

    override fun findAllItemByShortDescription(shortDescription: String): List<Item> {
        return itemRepository.findAllItemByShortDescription(shortDescription).stream().map { it.toItem() }.toList()
    }

    override fun findAllWithStock(branchId: Long): List<Item> {
        val stock = stockRepository.findAllInStock(branchId)

        return stock.mapNotNull {
            val item = itemRepository.findBySku(it.sku)
            item?.availableStock = it.stockTotal
            item
        }
    }

    override fun findItemById(id: Long): Item? {
        return itemRepository.findById(id)
    }

    override fun findAllItems(): List<Item> {
        return itemRepository.findAllItem()
    }

    private fun Item.toItemDao(): ItemRepresentation {
        return ItemRepresentation(id, sku.toUpperCase(), shortDescription.toUpperCase(), description.toUpperCase(), brandId, categoryId, uomSale.toUpperCase(), price, cost, imported, state.toUpperCase())
    }

    private fun ItemRepresentation.toItem(): Item {
        return Item(id, sku, shortDescription, description, brandId, categoryId, uomSale, price, cost, imported, state, 0)
    }
}



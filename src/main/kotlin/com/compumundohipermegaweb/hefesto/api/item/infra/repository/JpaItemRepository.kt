package com.compumundohipermegaweb.hefesto.api.item.infra.repository

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation

class JpaItemRepository(private val itemDao: ItemDao): ItemRepository {

    override fun save(item: ItemRepresentation): ItemRepresentation {
        return itemDao.save(item)
    }

    override fun findAllItemByShortDescription(description: String): List<ItemRepresentation> {
        return itemDao.findAllItemByDescription("%${description.toUpperCase()}%")
    }

    override fun findBySku(sku: String): Item? {
        val itemDao = itemDao.findBySku(sku)

        return itemDao?.toItem()
    }

    override fun findById(id: Long): Item? {
        val item = itemDao.findById(id)
        if(item.isPresent){
            return item.get().toItem()
        }
        return null
    }

    override fun findAllItem(): List<Item> {
        return itemDao.findAll().map { it.toItem() }
    }

    override fun deleteBySku(sku: String) {
        itemDao.deleteBySku(sku)
    }

    private fun ItemRepresentation.toItem(): Item {
        return Item(id, sku, shortDescription, description, brandId, categoryId, uomSale, price, cost, imported, state, 0)
    }
}

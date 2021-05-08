package com.compumundohipermegaweb.hefesto.api.item.infrastructure

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemRepository
import kotlin.streams.toList

class JpaItemRepository(private val repository: SpringDataItemRepository): ItemRepository {
    override fun save(item: Item): Item {
        return repository.save(item.toDao()).toItem()
    }

    override fun findAllItemByShortDescription(shortDescription: String): List<Item>{
        return repository.findAllByShortDescription(shortDescription).stream()
            .map { it.toItem() }.toList()
    }
    private fun Item.toDao(): ItemDao {
        return ItemDao(0L, "", shortDescription, longDescription, measure, stockTotal, securityStock, minimumStock, supplier, cost, unitPrice)
    }

    private fun ItemDao.toItem(): Item {
        return Item(id, sku, shortDescription, longDescription, measure, stockTotal, minimumStock, securityStock, supplier, cost, unitPrice)
    }

}




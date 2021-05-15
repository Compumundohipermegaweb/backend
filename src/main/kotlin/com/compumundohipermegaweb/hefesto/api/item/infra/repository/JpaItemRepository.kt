package com.compumundohipermegaweb.hefesto.api.item.infra.repository

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao

class JpaItemRepository(private val repository: SpringDataItemRepository): ItemRepository {

    override fun save(item: ItemDao): ItemDao {
        return repository.save(item)
    }

    override fun findAllItemByShortDescription(shortDescription: String): List<ItemDao> {
        return repository.findAllByShortDescription(shortDescription)
    }
}
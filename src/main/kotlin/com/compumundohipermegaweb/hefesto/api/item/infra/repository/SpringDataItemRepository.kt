package com.compumundohipermegaweb.hefesto.api.item.infra.repository

import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemDao
import org.springframework.data.repository.CrudRepository

interface SpringDataItemRepository: CrudRepository<ItemDao, Long> {
    fun findAllByShortDescription(shortDescription: String): List<ItemDao>
}
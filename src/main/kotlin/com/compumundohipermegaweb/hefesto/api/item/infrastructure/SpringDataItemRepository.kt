package com.compumundohipermegaweb.hefesto.api.item.infrastructure

import org.springframework.data.repository.CrudRepository

interface SpringDataItemRepository: CrudRepository<ItemDao, Long> {
    fun findAllByShortDescription(shortDescription: String): List<ItemDao>
}
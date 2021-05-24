package com.compumundohipermegaweb.hefesto.api.item.infra.repository

import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataItemRepository: CrudRepository<ItemRepresentation, Long> {
    @Query("SELECT * FROM ITEM WHERE SHORT_DESCRIPTION LIKE ?1 OR DESCRIPTION LIKE ?1", nativeQuery = true)
    fun findAllItemByDescription(description: String): List<ItemRepresentation>
    fun findBySku(sku: String): ItemRepresentation?
}
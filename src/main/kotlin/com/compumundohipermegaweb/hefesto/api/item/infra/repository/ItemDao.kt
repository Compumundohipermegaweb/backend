package com.compumundohipermegaweb.hefesto.api.item.infra.repository

import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface ItemDao: CrudRepository<ItemRepresentation, Long> {
    @Query("SELECT * FROM ITEM WHERE SHORT_DESCRIPTION LIKE ?1 OR DESCRIPTION LIKE ?1", nativeQuery = true)
    fun findAllItemByDescription(description: String): List<ItemRepresentation>
    fun findBySku(sku: String): ItemRepresentation?

    @Modifying
    @Transactional
    @Query("delete from ItemRepresentation i where i.sku = :sku")
    fun deleteBySku(sku: String)

    @Modifying
    @Transactional
    @Query("Update ItemRepresentation i set i.cost = :newCost where i.sku = :sku")
    fun updateCostBySku(sku: String, newCost: Double)
}
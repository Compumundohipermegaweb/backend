package com.compumundohipermegaweb.hefesto.api.category.infra.repository

import com.compumundohipermegaweb.hefesto.api.category.infra.representation.CategoryRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface CategoryDao: CrudRepository<CategoryRepresentation, Long> {

    @Transactional
    @Modifying
    @Query("update CategoryRepresentation c set c.deleted = true where c.id = :id")
    fun logicDeleteById(id: Long)

    fun findAllByDeleted(deleted: Boolean): List<CategoryRepresentation>
}

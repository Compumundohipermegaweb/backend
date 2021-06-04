package com.compumundohipermegaweb.hefesto.api.brand.infra.repository

import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface BrandDao: CrudRepository<BrandRepresentation, Long> {

    @Transactional
    @Modifying
    @Query("update BrandRepresentation b set b.deleted = true where b.id = :id")
    fun updateDeletedById(id: Long)

    fun findAllByDeleted(deleted: Boolean): List<BrandRepresentation>
}

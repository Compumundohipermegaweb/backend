package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.reposiotory

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.representation.DispatchRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface DispatchDao: CrudRepository<DispatchRepresentation, Long> {

    @Modifying
    @Transactional
    @Query("update DispatchRepresentation d set d.status = :status where d.id = :id")
    fun updateStatus(id: Long, status: String)

}

package com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation.MeasurementUnitRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface MeasurementUnitDao: CrudRepository<MeasurementUnitRepresentation, Long> {

    @Transactional
    @Modifying
    @Query("update MeasurementUnitRepresentation mu set mu.deleted = true where mu.id = :id")
    fun updateDeletedById(id: Long)

    fun findAllByDeleted(deleted: Boolean): List<MeasurementUnitRepresentation>
}

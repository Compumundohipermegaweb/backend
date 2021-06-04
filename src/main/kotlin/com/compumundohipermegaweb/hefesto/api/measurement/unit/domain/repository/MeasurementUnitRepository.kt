package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit

interface MeasurementUnitRepository {
    fun findAll(): List<MeasurementUnit>
    fun save(measurementUnit: MeasurementUnit): MeasurementUnit
    fun logicDelete(id: Long)
}

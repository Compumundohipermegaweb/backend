package com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation.MeasurementUnitRepresentation

class JpaMeasurementUnitRepository(private val measurementUnitDao: MeasurementUnitDao): MeasurementUnitRepository {

    override fun findAll(): List<MeasurementUnit> {
        val measurements = measurementUnitDao.findAllByDeleted(false)
        return measurements.map { it.toMeasurementUnit() }
    }

    override fun save(measurementUnit: MeasurementUnit): MeasurementUnit {
        val measurementUnitRepresentation = measurementUnit.toRepresentation()
        return measurementUnitDao.save(measurementUnitRepresentation).toMeasurementUnit()
    }

    override fun logicDelete(id: Long) {
        measurementUnitDao.updateDeletedById(id)
    }


    private fun MeasurementUnitRepresentation.toMeasurementUnit() = MeasurementUnit(id, name, description)

    private fun MeasurementUnit.toRepresentation() = MeasurementUnitRepresentation(id, name, description, false)
}

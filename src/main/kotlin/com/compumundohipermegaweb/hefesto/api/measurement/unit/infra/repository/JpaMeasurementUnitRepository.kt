package com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation.MeasurementUnitRepresentation

class JpaMeasurementUnitRepository(private val measurementUnitDao: MeasurementUnitDao): MeasurementUnitRepository {

    override fun findAll(): List<MeasurementUnit> {
        val measurements = measurementUnitDao.findAll()
        return measurements.map { it.toMeasurementUnit() }
    }


    private fun MeasurementUnitRepresentation.toMeasurementUnit() =
            MeasurementUnit(
                    id = id,
                    name = name,
                    description = description)
}

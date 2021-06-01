package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository

class FindAllMeasurementUnits(private val measurementUnitRepository: MeasurementUnitRepository) {

    operator fun invoke(): List<MeasurementUnit> {
        return measurementUnitRepository.findAll()
    }
}
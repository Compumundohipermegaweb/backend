package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository

class LogicDeleteMeasurementUnit(private val measurementUnitRepository: MeasurementUnitRepository) {

    operator fun invoke(id: Long) {
        measurementUnitRepository.logicDelete(id)
    }
}
package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository

class CreateMeasurementUnit(private val measurementUnitRepository: MeasurementUnitRepository) {

    operator fun invoke(actionData: ActionData): MeasurementUnit {
        val measurementUnit = MeasurementUnit(0L, actionData.name, actionData.description)
        return measurementUnitRepository.save(measurementUnit)
    }

    data class ActionData(val name: String, val description: String)
}
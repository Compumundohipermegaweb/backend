package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class UpdateMeasurementUnitShould {

    private lateinit var measurementUnitRepository: MeasurementUnitRepository
    private lateinit var updateMeasurementUnit: UpdateMeasurementUnit

    private lateinit var measurementUnitUpdated: MeasurementUnit

    @Test
    fun `update the measurement unit`() {
        givenMeasurementUnitRepository()
        givenUpdateMeasurementUnit()

        whenUpdatingMeasurementUnit()

        thenMeasurementUnitHasBeenUpdated()
    }

    private fun givenMeasurementUnitRepository() {
        measurementUnitRepository = mock()
    }

    private fun givenUpdateMeasurementUnit() {
        updateMeasurementUnit = UpdateMeasurementUnit(measurementUnitRepository)
    }

    private fun whenUpdatingMeasurementUnit() {
        measurementUnitUpdated = updateMeasurementUnit(UPDATE_MEASUREMENT_UNIT_ACTION_DATA)
    }

    private fun thenMeasurementUnitHasBeenUpdated() {
        verify(measurementUnitRepository).save(MEASUREMENT_UNIT_TO_UPDATE)
    }

    private companion object {
        val UPDATE_MEASUREMENT_UNIT_ACTION_DATA = UpdateMeasurementUnit.ActionData(id = 0L, name = "", description = "")
        val MEASUREMENT_UNIT_TO_UPDATE =
                MeasurementUnit(
                        id = UPDATE_MEASUREMENT_UNIT_ACTION_DATA.id,
                        name = UPDATE_MEASUREMENT_UNIT_ACTION_DATA.name,
                        description = UPDATE_MEASUREMENT_UNIT_ACTION_DATA.description)
    }
}
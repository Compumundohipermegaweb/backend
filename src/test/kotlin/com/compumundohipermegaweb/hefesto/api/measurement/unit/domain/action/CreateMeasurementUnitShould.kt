package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class CreateMeasurementUnitShould {

    private lateinit var measurementUnitRepository: MeasurementUnitRepository
    private lateinit var createMeasurementUnit: CreateMeasurementUnit

    private lateinit var createdMeasurementUnit: MeasurementUnit

    @Test
    fun `create the measurement unit`() {
        givenMeasurementUnitRepository()
        givenCreateMeasurementUnit()

        whenCreatingMeasurementUnit()

        thenMeasurementUnitHasBeenCreated()
    }

    private fun givenMeasurementUnitRepository() {
        measurementUnitRepository = mock()
    }

    private fun givenCreateMeasurementUnit() {
        createMeasurementUnit = CreateMeasurementUnit(measurementUnitRepository)
    }

    private fun whenCreatingMeasurementUnit() {
        createdMeasurementUnit = createMeasurementUnit(actionData = CREATE_MEASUREMENT_UNIT_ACTION_DATA)
    }

    private fun thenMeasurementUnitHasBeenCreated() {
        verify(measurementUnitRepository).save(MEASUREMENT_UNIT_TO_SAVE)
    }

    private companion object {
        val CREATE_MEASUREMENT_UNIT_ACTION_DATA =
                CreateMeasurementUnit.ActionData(name = "", description = "")

        val MEASUREMENT_UNIT_TO_SAVE =
                MeasurementUnit(0L, CREATE_MEASUREMENT_UNIT_ACTION_DATA.name, CREATE_MEASUREMENT_UNIT_ACTION_DATA.description)
    }
}
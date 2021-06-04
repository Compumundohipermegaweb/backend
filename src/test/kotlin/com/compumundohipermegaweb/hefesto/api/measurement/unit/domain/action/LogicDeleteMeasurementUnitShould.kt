package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test

class LogicDeleteMeasurementUnitShould {

    private lateinit var measurementUnitRepository: MeasurementUnitRepository
    private lateinit var logicDeleteMeasurementUnit: LogicDeleteMeasurementUnit

    @Test
    fun `logically delete measurement unit`() {
        givenMeasurementUnitRepository()
        givenLogicDeleteMeasurementUnit()

        whenLogicDeletingMeasurementUnit()

        thenMeasurementUnitHasBeenLogicDeleted()
    }

    private fun givenMeasurementUnitRepository() {
        measurementUnitRepository = mock()
    }

    private fun givenLogicDeleteMeasurementUnit() {
        logicDeleteMeasurementUnit = LogicDeleteMeasurementUnit(measurementUnitRepository)
    }

    private fun whenLogicDeletingMeasurementUnit() {
        logicDeleteMeasurementUnit(1L)
    }

    private fun thenMeasurementUnitHasBeenLogicDeleted() {
        measurementUnitRepository.logicDelete(1L)
    }
}
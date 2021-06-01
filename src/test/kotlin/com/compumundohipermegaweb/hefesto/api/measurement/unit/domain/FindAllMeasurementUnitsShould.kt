package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.FindAllMeasurementUnits
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class FindAllMeasurementUnitsShould {

    private lateinit var measurementUnitRepository: MeasurementUnitRepository
    private lateinit var findAllMeasurementUnits: FindAllMeasurementUnits

    private lateinit var measurementUnitsFound: List<MeasurementUnit>

    @Test
    fun `find all measurement units`() {
        givenMeasurmentUnitRepository()
        givenFindAllMeasurementUnits()

        whenFindingAllMeasurementUnits()

        thenMeasurementUnitsWhereFound()
    }

    private fun givenMeasurmentUnitRepository() {
        measurementUnitRepository = mock()
    }

    private fun givenFindAllMeasurementUnits() {
        findAllMeasurementUnits = FindAllMeasurementUnits(measurementUnitRepository)
    }

    private fun whenFindingAllMeasurementUnits() {
        measurementUnitsFound = findAllMeasurementUnits()
    }

    private fun thenMeasurementUnitsWhereFound() {
        verify(measurementUnitRepository).findAll()
    }
}
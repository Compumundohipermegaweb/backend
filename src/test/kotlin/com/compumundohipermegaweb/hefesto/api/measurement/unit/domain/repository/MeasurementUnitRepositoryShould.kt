package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.JpaMeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.MeasurementUnitDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class JpaMeasurementUnitRepositoryShould {

    private lateinit var measurementUnitDao: MeasurementUnitDao
    private lateinit var measurementUnitRepository: MeasurementUnitRepository

    private lateinit var measurementUnitsFound: List<MeasurementUnit>

    @Test
    fun `find all`() {
        givenMeasurementUnitDao()
        givenMeasurementUnitRepository()

        whenFindingAllMeasurementUnits()

        thenAllMeasurementUnitsWhereFound()
    }

    private fun givenMeasurementUnitDao() {
        measurementUnitDao = mock()
    }

    private fun givenMeasurementUnitRepository() {
        measurementUnitRepository = JpaMeasurementUnitRepository(measurementUnitDao)
    }

    private fun whenFindingAllMeasurementUnits() {
        measurementUnitsFound = measurementUnitRepository.findAll()
    }

    private fun thenAllMeasurementUnitsWhereFound() {
        verify(measurementUnitDao).findAll()
    }
}
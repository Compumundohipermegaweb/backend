package com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.JpaMeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.MeasurementUnitDao
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation.MeasurementUnitRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaMeasurementUnitRepositoryShould {

    private lateinit var measurementUnitDao: MeasurementUnitDao
    private lateinit var measurementUnitRepository: MeasurementUnitRepository

    private lateinit var measurementUnitsFound: List<MeasurementUnit>
    private lateinit var measurementUnitSaved: MeasurementUnit

    @Test
    fun `find all`() {
        givenMeasurementUnitDao()
        givenMeasurementUnitRepository()

        whenFindingAllMeasurementUnits()

        thenAllMeasurementUnitsWhereFound()
    }

    @Test
    fun `save a measurement unit`() {
        givenMeasurementUnitDao()
        givenMeasurementUnitRepository()

        whenUpdatingMeasurementUnit()

        thenMeasurementUnitHasBeenUpdated()
    }

    @Test
    fun `logic delete a measurement unit`() {
        givenMeasurementUnitDao()
        givenMeasurementUnitRepository()

        whenLogicDeletingMeasurementUnit()

        thenMeasurementUnitWasLogicDeleted()
    }

    private fun givenMeasurementUnitDao() {
        measurementUnitDao = mock()
        `when`(measurementUnitDao.save(MEASUREMENT_UNIT_REPRESENTATION_TO_SAVE))
                .thenReturn(MEASUREMENT_UNIT_REPRESENTATION_TO_SAVE)
    }

    private fun givenMeasurementUnitRepository() {
        measurementUnitRepository = JpaMeasurementUnitRepository(measurementUnitDao)
    }

    private fun whenFindingAllMeasurementUnits() {
        measurementUnitsFound = measurementUnitRepository.findAll()
    }

    private fun whenUpdatingMeasurementUnit() {
        measurementUnitSaved = measurementUnitRepository.save(MEASUREMENT_UNIT_TO_SAVE)
    }

    private fun whenLogicDeletingMeasurementUnit() {
        measurementUnitRepository.logicDelete(1L)
    }

    private fun thenAllMeasurementUnitsWhereFound() {
        verify(measurementUnitDao).findAllByDeleted(false)
    }

    private fun thenMeasurementUnitHasBeenUpdated() {
        verify(measurementUnitDao).save(MEASUREMENT_UNIT_REPRESENTATION_TO_SAVE)
    }

    private fun thenMeasurementUnitWasLogicDeleted() {
        verify(measurementUnitDao).updateDeletedById(1L)
    }

    private companion object {
        val MEASUREMENT_UNIT_TO_SAVE =
                MeasurementUnit(0L, "", "")
        val MEASUREMENT_UNIT_REPRESENTATION_TO_SAVE =
                MeasurementUnitRepresentation(0L, MEASUREMENT_UNIT_TO_SAVE.name, MEASUREMENT_UNIT_TO_SAVE.description, false)
    }
}
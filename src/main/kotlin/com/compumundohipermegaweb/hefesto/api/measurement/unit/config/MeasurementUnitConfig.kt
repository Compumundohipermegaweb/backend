package com.compumundohipermegaweb.hefesto.api.measurement.unit.config

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.CreateMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.FindAllMeasurementUnits
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.LogicDeleteMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.UpdateMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.repository.MeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.JpaMeasurementUnitRepository
import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository.MeasurementUnitDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MeasurementUnitConfig {

    @Bean
    fun findAllMeasurementUnits(measurementUnitRepository: MeasurementUnitRepository): FindAllMeasurementUnits {
        return FindAllMeasurementUnits(measurementUnitRepository)
    }

    @Bean
    fun createMeasurementUnit(measurementUnitRepository: MeasurementUnitRepository): CreateMeasurementUnit {
        return CreateMeasurementUnit(measurementUnitRepository)
    }

    @Bean
    fun updateMeasurementUnit(measurementUnitRepository: MeasurementUnitRepository): UpdateMeasurementUnit {
        return UpdateMeasurementUnit(measurementUnitRepository)
    }

    @Bean
    fun logicDeleteMeasurementUnit(measurementUnitRepository: MeasurementUnitRepository): LogicDeleteMeasurementUnit {
        return LogicDeleteMeasurementUnit(measurementUnitRepository)
    }

    @Bean
    fun measurementUnitRepository(measurementUnitDao: MeasurementUnitDao): MeasurementUnitRepository{
        return JpaMeasurementUnitRepository(measurementUnitDao)
    }
}
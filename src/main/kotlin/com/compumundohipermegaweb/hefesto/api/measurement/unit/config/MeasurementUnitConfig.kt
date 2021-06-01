package com.compumundohipermegaweb.hefesto.api.measurement.unit.config

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.FindAllMeasurementUnits
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
    fun measurementUnitRepository(measurementUnitDao: MeasurementUnitDao): MeasurementUnitRepository{
        return JpaMeasurementUnitRepository(measurementUnitDao)
    }
}
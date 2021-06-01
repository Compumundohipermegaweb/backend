package com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.repository

import com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation.MeasurementUnitRepresentation
import org.springframework.data.repository.CrudRepository

interface MeasurementUnitDao: CrudRepository<MeasurementUnitRepresentation, Long>

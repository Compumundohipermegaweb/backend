package com.compumundohipermegaweb.hefesto.api.measurement.unit.rest

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.FindAllMeasurementUnits
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.GetAllMeasurementUnitsResponse
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.MeasurementUnitResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/measurement-units")
class MeasurementUnitController(
        private val findAllMeasurementUnits: FindAllMeasurementUnits) {

    @GetMapping
    fun getAll(): ResponseEntity<GetAllMeasurementUnitsResponse> {
        val units = findAllMeasurementUnits().map { it.toResponse() }
        return ResponseEntity.ok(GetAllMeasurementUnitsResponse(units))
    }

    private fun MeasurementUnit.toResponse() = MeasurementUnitResponse(id, name, description)
}
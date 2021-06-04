package com.compumundohipermegaweb.hefesto.api.measurement.unit.rest

import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.CreateMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.FindAllMeasurementUnits
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.LogicDeleteMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.action.UpdateMeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.domain.model.MeasurementUnit
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.CreateMeasurementUnitRequest
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.GetAllMeasurementUnitsResponse
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.MeasurementUnitResponse
import com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation.UpdateMeasurementUnitRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/measurement-units")
class MeasurementUnitController(
        private val findAllMeasurementUnits: FindAllMeasurementUnits,
        private val createMeasurementUnit: CreateMeasurementUnit,
        private val updateMeasurementUnit: UpdateMeasurementUnit,
        private val logicDeleteMeasurementUnit: LogicDeleteMeasurementUnit) {

    @GetMapping
    fun getAll(): ResponseEntity<GetAllMeasurementUnitsResponse> {
        val units = findAllMeasurementUnits().map { it.toResponse() }
        return ResponseEntity.ok(GetAllMeasurementUnitsResponse(units))
    }

    @PostMapping
    fun post(@RequestBody request: CreateMeasurementUnitRequest): ResponseEntity<MeasurementUnitResponse> {
        val actionData = CreateMeasurementUnit.ActionData(request.name, request.description)
        val measurementUnit = createMeasurementUnit(actionData).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(measurementUnit)
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: Long, @RequestBody request: UpdateMeasurementUnitRequest): ResponseEntity<MeasurementUnitResponse> {
        val actionData = UpdateMeasurementUnit.ActionData(id, request.name, request.description)
        val measurementUnit = updateMeasurementUnit(actionData).toResponse()
        return ResponseEntity.ok(measurementUnit)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        logicDeleteMeasurementUnit(id)
        return ResponseEntity.noContent().build()
    }

    private fun MeasurementUnit.toResponse() = MeasurementUnitResponse(id, name, description)
}
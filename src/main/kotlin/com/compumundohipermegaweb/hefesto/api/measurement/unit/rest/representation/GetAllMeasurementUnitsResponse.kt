package com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllMeasurementUnitsResponse(
        @JsonProperty("units") val units: List<MeasurementUnitResponse>,)

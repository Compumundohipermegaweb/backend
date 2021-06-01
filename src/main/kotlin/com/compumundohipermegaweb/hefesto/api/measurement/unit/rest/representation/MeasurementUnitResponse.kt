package com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class MeasurementUnitResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String)
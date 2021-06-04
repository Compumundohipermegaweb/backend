package com.compumundohipermegaweb.hefesto.api.measurement.unit.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpdateMeasurementUnitRequest(
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String)
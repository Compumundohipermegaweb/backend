package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class AlertResponse(@JsonProperty("id") val id: Long,
                         @JsonProperty("time") val time: LocalTime,
                         @JsonProperty("process_description") val processDescription: String)

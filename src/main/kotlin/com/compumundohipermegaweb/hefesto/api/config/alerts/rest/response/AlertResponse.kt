package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AlertResponse(@JsonProperty("id") val id: Long,
                         @JsonProperty("time") val time: String,
                         @JsonProperty("process_description") val processDescription: String)

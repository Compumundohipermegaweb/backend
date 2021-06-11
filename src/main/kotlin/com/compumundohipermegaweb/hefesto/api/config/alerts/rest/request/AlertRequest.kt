package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AlertRequest(@JsonProperty("id") val id: Long,
                        @JsonProperty("time") var time: String,
                        @JsonProperty("alert_description") val alertDescription: String)

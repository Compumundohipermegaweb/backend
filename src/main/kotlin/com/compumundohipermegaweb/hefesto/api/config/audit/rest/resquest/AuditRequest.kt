package com.compumundohipermegaweb.hefesto.api.config.audit.rest.resquest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AuditRequest(@JsonProperty("id") val id: Long,
                        @JsonProperty("name") val name: String)

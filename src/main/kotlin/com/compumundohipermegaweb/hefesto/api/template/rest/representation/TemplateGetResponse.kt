package com.compumundohipermegaweb.hefesto.api.template.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class TemplateGetResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("output") val output: String)

package com.compumundohipermegaweb.hefesto.api.brand.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateBrandRequest(
        @JsonProperty("name") val name: String)
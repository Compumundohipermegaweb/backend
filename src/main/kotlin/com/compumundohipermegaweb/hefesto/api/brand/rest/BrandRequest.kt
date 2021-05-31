package com.compumundohipermegaweb.hefesto.api.brand.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateBrandRequest(
        @JsonProperty("name") val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpdateBrandRequest(
        @JsonProperty("id") val id: Long,
        @JsonProperty("name") val name: String)
package com.compumundohipermegaweb.hefesto.api.category.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateCategoryRequests(
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String)

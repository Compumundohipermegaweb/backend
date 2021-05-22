package com.compumundohipermegaweb.hefesto.api.card.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CardResponse(@JsonProperty("id") val id: Long,
                        @JsonProperty("name") val name: String,
                        @JsonProperty("state") val state: String)

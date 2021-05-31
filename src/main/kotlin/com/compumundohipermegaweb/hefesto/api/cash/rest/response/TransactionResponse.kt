package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionResponse(@JsonProperty("id") val id: Long,
                               @JsonProperty("movement_id") val movementId: Long,
                               @JsonProperty("description") val description: String,
                               @JsonProperty("state") val state: String)

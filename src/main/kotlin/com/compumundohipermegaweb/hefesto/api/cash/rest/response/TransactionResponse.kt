package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionResponse(@JsonProperty("movement_id") val movementId: Long,
                               @JsonProperty("transaction_id") val id: Long,
                               @JsonProperty("description") val description: String,
                               @JsonProperty("state") val state: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionsResponse(@JsonProperty("transactions") val transactions: List<TransactionResponse>)

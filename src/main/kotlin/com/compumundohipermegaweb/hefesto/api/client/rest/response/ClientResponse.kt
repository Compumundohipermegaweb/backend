package com.compumundohipermegaweb.hefesto.api.client.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientResponse(@JsonProperty("id") val id: Long,
                          @JsonProperty("document_number") val documentNumber: String,
                          @JsonProperty("first_name") val firstName: String,
                          @JsonProperty("last_name") val lastName: String,
                          @JsonProperty("state") val state: String,
                          @JsonProperty("credit_limit") val creditLimit: Double,
                          @JsonProperty("email") val email: String,
                          @JsonProperty("contact_number") val contactNumber: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientsResponse(@JsonProperty("clients") val clients: List<ClientResponse>)
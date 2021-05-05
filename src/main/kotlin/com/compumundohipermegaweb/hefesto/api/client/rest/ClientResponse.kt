package com.compumundohipermegaweb.hefesto.api.client.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientResponse(@JsonProperty("document_number") val documentNumber: String,
                          @JsonProperty("fist_name")val firstName: String,
                          @JsonProperty("last_name")val lastName: String,
                          @JsonProperty("sur_name")val surName: String,
                          @JsonProperty("category")var category: String,
                          @JsonProperty("email")var email: String,
                          @JsonProperty("contact_number")var contactNumber: String)
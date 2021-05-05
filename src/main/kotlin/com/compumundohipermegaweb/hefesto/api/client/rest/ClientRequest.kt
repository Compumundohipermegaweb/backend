package com.compumundohipermegaweb.hefesto.api.client.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientRequest(@JsonProperty val documentNumber: String,
                         @JsonProperty val firstName: String,
                         @JsonProperty val lastName: String,
                         @JsonProperty val surName: String,
                         @JsonProperty var category: String,
                         @JsonProperty var email: String,
                         @JsonProperty var contactNumber: String)
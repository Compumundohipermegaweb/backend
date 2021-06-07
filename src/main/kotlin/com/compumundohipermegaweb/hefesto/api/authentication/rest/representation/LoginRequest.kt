package com.compumundohipermegaweb.hefesto.api.authentication.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginRequest(
    @JsonProperty("username")  val username: String,
    @JsonProperty("password") val password: String)
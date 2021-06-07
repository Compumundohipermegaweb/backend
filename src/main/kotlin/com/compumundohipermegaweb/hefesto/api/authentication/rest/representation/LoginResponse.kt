package com.compumundohipermegaweb.hefesto.api.authentication.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("user") val user: String,
    @JsonProperty("token") val token: TokenResponse)

data class TokenResponse(
    @JsonProperty("code") val code: String,
    @JsonProperty("role") val role: String)
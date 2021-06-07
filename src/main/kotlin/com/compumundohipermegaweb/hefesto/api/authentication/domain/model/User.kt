package com.compumundohipermegaweb.hefesto.api.authentication.domain.model

data class User(val code: String, val username: String, val password: String, val role: Role)
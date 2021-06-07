package com.compumundohipermegaweb.hefesto.api.authentication.domain.model

data class Token(val code: String, val role: Role)

enum class Role {
    VENDEDOR, CAJERO, SUPERVISOR, GERENTE, ADMIN
}
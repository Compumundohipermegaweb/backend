package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

interface PasswordEncodingService {
    fun encode(password: String): String
}

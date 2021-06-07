package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

class DefaultPasswordAuthenticationService(private val passwordEncodingService: PasswordEncodingService): PasswordAuthenticationService {

    override fun authenticate(userPassword: String, password: String): Boolean {
        return userPassword == passwordEncodingService.encode(password)
    }
}
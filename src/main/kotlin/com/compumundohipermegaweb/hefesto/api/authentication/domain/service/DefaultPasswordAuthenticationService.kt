package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

class DefaultPasswordAuthenticationService(private val passwordEncodingService: PasswordEncodingService): PasswordAuthenticationService {

    override fun authenticate(userPassword: String, givenPassword: String): Boolean {
        return userPassword == passwordEncodingService.encode(givenPassword)
    }
}
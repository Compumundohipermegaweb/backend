package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

interface PasswordAuthenticationService {

    fun authenticate(userPassword: String, givenPassword: String): Boolean

}

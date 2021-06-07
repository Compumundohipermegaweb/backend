package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultUserAuthenticationServiceShould {

    private lateinit var passwordEncodingService: PasswordEncodingService
    private lateinit var userAuthenticationService: UserAuthenticationService

    private var result: Boolean = false

    @Test
    fun `use sha256 encoding for password authentication` () {
        givenPasswordEncodingService()
        givenUserAuthenticationService()

        whenAuthenticating()

        thenPasswordHasBeenEncoded()
    }

    @Test
    fun `verify that user password matches the given password` () {
        givenPasswordEncodingService()
        givenUserAuthenticationService()

        whenAuthenticating()

        thenUserIsAuthenticated()
    }

    @Test
    fun `return false if the password does not match` () {
        givenPasswordEncodingService()
        givenUserAuthenticationService()

        result = userAuthenticationService.authenticate(USER, WRONG_PASSWORD)

        then(result).isFalse
    }

    private fun thenUserIsAuthenticated() {
        then(result).isTrue
    }

    private fun givenPasswordEncodingService() {
        passwordEncodingService = mock()
        `when`(passwordEncodingService.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD)
        `when`(passwordEncodingService.encode(WRONG_PASSWORD)).thenReturn(ENCODED_WRONG_PASSWORD)
    }

    private fun givenUserAuthenticationService() {
        userAuthenticationService = DefaultUserAuthenticationService(passwordEncodingService)
    }

    private fun whenAuthenticating() {
        result = userAuthenticationService.authenticate(USER, PASSWORD)
    }

    private fun thenPasswordHasBeenEncoded() {
        verify(passwordEncodingService).encode(PASSWORD)
    }

    private companion object {
        const val PASSWORD = "admin"
        const val ENCODED_PASSWORD = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"
        const val WRONG_PASSWORD = "adnim"
        const val ENCODED_WRONG_PASSWORD = "b7f215cde06fa700ce5dc1efb1fe349055f82987b0e4ba6cb95166e5996bc6b3"
        val USER = User("", "", ENCODED_PASSWORD, Role.ADMIN)
    }
}
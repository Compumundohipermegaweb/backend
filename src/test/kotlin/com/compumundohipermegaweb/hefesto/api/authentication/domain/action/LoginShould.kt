package com.compumundohipermegaweb.hefesto.api.authentication.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.exception.AuthenticationException
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Session
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.PasswordAuthenticationService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.catchThrowable
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class LoginShould {

    private lateinit var userRepository: UserRepository
    private lateinit var passwordAuthenticationService: PasswordAuthenticationService
    private lateinit var login: Login

    private lateinit var session: Session
    private var exceptionThrown: Throwable? = null

    @Test
    fun `return a session` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        whenLogin()

        theSessionHasBeenCreated()
    }

    @Test
    fun `create token to the user` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        whenLogin()

        thenSessionHasToken()
    }

    @Test
    fun `authenticate the user` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        whenLogin()

        thenPasswordHasBeenAuthenticated()
    }

    @Test
    fun `fail if the authentication failed` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        whenLoginWithInvalidPassword()

        thenAuthenticationFailed()
    }

    @Test
    fun `fail if the user cant be found` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        whenLoginWithInvalidUsername()

        thenAuthenticationFailed()
    }

    private fun givenUserAuthenticationService() {
        passwordAuthenticationService = mock()
        `when`(passwordAuthenticationService.authenticate(ADMIN_USER.password, ACTION_DATA.password)).thenReturn(true)
        `when`(passwordAuthenticationService.authenticate(ADMIN_USER.password, INVALID_PASSWORD_ACTION_DATA.password)).thenReturn(false)
    }

    private fun givenUserRepository() {
        userRepository = mock()
        `when`(userRepository.find(ACTION_DATA.username)).thenReturn(ADMIN_USER)
        `when`(userRepository.find(INVALID_USER_ACTION_DATA.username)).thenReturn(null)
    }

    private fun givenLogin() {
        login = Login(passwordAuthenticationService, userRepository)
    }

    private fun whenLogin() {
        session = login(ACTION_DATA)
    }

    private fun whenLoginWithInvalidPassword() {
        exceptionThrown = catchThrowable {
            session = login(INVALID_PASSWORD_ACTION_DATA)
        }
    }

    private fun whenLoginWithInvalidUsername() {
        exceptionThrown = catchThrowable {
            session = login(INVALID_USER_ACTION_DATA)
        }
    }

    private fun theSessionHasBeenCreated() {
        then(session).isNotNull
    }

    private fun thenSessionHasToken() {
        then(session.token).isNotNull
        then(session.user).isNotNull
    }

    private fun thenPasswordHasBeenAuthenticated() {
        verify(passwordAuthenticationService).authenticate(ADMIN_USER.password, ACTION_DATA.password)
    }

    private fun thenAuthenticationFailed() {
        then(exceptionThrown).isInstanceOf(AuthenticationException::class.javaObjectType)
    }

    private companion object {
        val ACTION_DATA = Login.ActionData(username = "email@example.com", password = "password")
        val INVALID_PASSWORD_ACTION_DATA = Login.ActionData(username = "email@example.com", password = "invalidPassword")
        val INVALID_USER_ACTION_DATA = Login.ActionData(username = "invaliduser@gmail.com", "password")
        val ADMIN_USER = User("", ACTION_DATA.username, ACTION_DATA.password, Role.ADMIN)
    }
}
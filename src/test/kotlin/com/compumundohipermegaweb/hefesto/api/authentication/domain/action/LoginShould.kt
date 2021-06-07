package com.compumundohipermegaweb.hefesto.api.authentication.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.exception.AuthenticationException
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Session
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.UserAuthenticationService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.catchThrowable
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class LoginShould {

    private lateinit var userRepository: UserRepository
    private lateinit var userAuthenticationService: UserAuthenticationService
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

        verify(userAuthenticationService).authenticate(ADMIN_USER, ACTION_DATA.password)
    }

    @Test
    fun `should fail if the authentication failed` () {
        givenUserRepository()
        givenUserAuthenticationService()
        givenLogin()

        exceptionThrown = catchThrowable {
            session = login(INVALID_USER_ACTION_DATA)
        }

        then(exceptionThrown).isNotNull
        then(exceptionThrown).isInstanceOf(AuthenticationException::class.java)
    }

    private fun givenUserAuthenticationService() {
        userAuthenticationService = mock()
        `when`(userAuthenticationService.authenticate(ADMIN_USER, ACTION_DATA.password)).thenReturn(true)
        `when`(userAuthenticationService.authenticate(ADMIN_USER, INVALID_USER_ACTION_DATA.password)).thenReturn(false)
    }

    private fun givenUserRepository() {
        userRepository = mock()
        `when`(userRepository.find(ACTION_DATA.user)).thenReturn(ADMIN_USER)
    }

    private fun givenLogin() {
        login = Login(userAuthenticationService, userRepository)
    }

    private fun whenLogin() {
        session = login(ACTION_DATA)
    }

    private fun theSessionHasBeenCreated() {
        then(session).isNotNull
    }

    private fun thenSessionHasToken() {
        then(session.token).isNotNull
        then(session.user).isNotNull
    }

    private companion object {
        val ACTION_DATA = Login.ActionData(user = "email@example.com", password = "password")
        val INVALID_USER_ACTION_DATA = Login.ActionData(user = "email@example.com", password = "invalidPassword")
        val ADMIN_USER = User("", ACTION_DATA.user, ACTION_DATA.password, Role.ADMIN)
    }
}
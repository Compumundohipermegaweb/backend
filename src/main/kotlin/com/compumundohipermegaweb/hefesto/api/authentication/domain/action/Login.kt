package com.compumundohipermegaweb.hefesto.api.authentication.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.exception.AuthenticationException
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Session
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Token
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.PasswordAuthenticationService

class Login(private val passwordAuthenticationService: PasswordAuthenticationService,
            private val userRepository: UserRepository) {

    operator fun invoke(actionData: ActionData): Session {
        val user = userRepository.find(actionData.username) ?: throw AuthenticationException(FAILED_AUTHENTICATION_MESSAGE)

        if(!passwordAuthenticationService.authenticate(user.password, actionData.password)) {
            throw AuthenticationException(FAILED_AUTHENTICATION_MESSAGE)
        }

        return Session(actionData.username, Token(code = user.code, role = user.role))
    }

    data class ActionData(val username: String, val password: String)

    private companion object {
        const val FAILED_AUTHENTICATION_MESSAGE = "¡Usuario o contraseña inválidos!"
    }
}
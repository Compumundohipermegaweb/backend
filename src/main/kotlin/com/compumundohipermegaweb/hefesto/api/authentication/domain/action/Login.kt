package com.compumundohipermegaweb.hefesto.api.authentication.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.exception.AuthenticationException
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Session
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Token
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.UserAuthenticationService

class Login(private val userAuthenticationService: UserAuthenticationService,
            private val userRepository: UserRepository) {

    operator fun invoke(actionData: ActionData): Session {
        val user = userRepository.find(actionData.user)

        if(!userAuthenticationService.authenticate(user, actionData.password)) {
            throw AuthenticationException("Usuario o contraseña inválidos!")
        }

        return Session(actionData.user, Token(code = user.code, role = user.role))
    }

    data class ActionData(val user: String, val password: String)
}
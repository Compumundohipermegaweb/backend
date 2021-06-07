package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User

class DefaultUserAuthenticationService(private val passwordEncodingService: PasswordEncodingService): UserAuthenticationService {
    override fun authenticate(user: User, password: String): Boolean {
        return user.password == passwordEncodingService.encode(password)
    }
}
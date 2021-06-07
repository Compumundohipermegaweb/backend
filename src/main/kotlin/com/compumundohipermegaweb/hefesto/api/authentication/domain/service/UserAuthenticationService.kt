package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User

interface UserAuthenticationService {

    fun authenticate(user: User, password: String): Boolean

}

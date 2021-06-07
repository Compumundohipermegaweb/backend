package com.compumundohipermegaweb.hefesto.api.authentication.domain.repository

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User


interface UserRepository {
    fun find(username: String): User?
}

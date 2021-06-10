package com.compumundohipermegaweb.hefesto.api.authentication.infra

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.infra.representation.UserRepresentation

class JpaUserRepository(private val userDao: UserDao): UserRepository {

    override fun find(username: String): User? {
        val userRepresentation = userDao.findByUsername(username)
        return userRepresentation?.toUser()
    }

    override fun findByCode(code: String): List<User> {
        val users = userDao.findByCode(code)
        if(users.isNotEmpty()) {
            return userDao.findByCode(code).map { it.toUser() }
        }
        return emptyList()
    }

    private fun UserRepresentation.toUser() = User(code, username, password, Role.valueOf(role))
}
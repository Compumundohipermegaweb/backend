package com.compumundohipermegaweb.hefesto.api.authentication.infra

import com.compumundohipermegaweb.hefesto.api.authentication.infra.representation.UserRepresentation
import org.springframework.data.repository.CrudRepository

interface UserDao: CrudRepository<UserRepresentation, Long> {

    fun findByUsername(username: String): UserRepresentation?
    fun findByCode(code: String): List<UserRepresentation>
}

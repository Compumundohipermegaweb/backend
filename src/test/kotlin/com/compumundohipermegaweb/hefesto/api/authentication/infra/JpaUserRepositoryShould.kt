package com.compumundohipermegaweb.hefesto.api.authentication.infra

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class JpaUserRepositoryShould {

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    private var userFound: User? = null
    private lateinit var usersFound: List<User>

    @Test
    fun `find by username` () {
        givenUserDao()
        givenUserRepository()

        whenFindingUser()

        thenUserIsFound()
    }

    @Test
    fun `find by code` () {
        givenUserDao()
        givenUserRepository()

        whenFindingUserByCode()

        thenUsersAreFoundByCode()
    }

    private fun givenUserDao() {
        userDao = mock()
    }

    private fun givenUserRepository() {
        userRepository = JpaUserRepository(userDao)
    }

    private fun whenFindingUser() {
        userFound = userRepository.find(USERNAME)
    }

    private fun whenFindingUserByCode() {
        usersFound = userRepository.findByCode("1")
    }

    private fun thenUserIsFound() {
        verify(userDao).findByUsername(USERNAME)
    }

    private fun thenUsersAreFoundByCode() {
        verify(userDao).findByCode("1")
    }

    private companion object {
        const val USERNAME = "username"
    }
}
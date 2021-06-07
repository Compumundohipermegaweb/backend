package com.compumundohipermegaweb.hefesto.api.authentication.config

import com.compumundohipermegaweb.hefesto.api.authentication.domain.action.Login
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.DefaultPasswordAuthenticationService
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.PasswordAuthenticationService
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.PasswordEncodingService
import com.compumundohipermegaweb.hefesto.api.authentication.domain.service.Sha256PasswordEncodingService
import com.compumundohipermegaweb.hefesto.api.authentication.infra.JpaUserRepository
import com.compumundohipermegaweb.hefesto.api.authentication.infra.UserDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthenticationConfig {

    @Bean
    fun login(passwordAuthenticationService: PasswordAuthenticationService, userRepository: UserRepository): Login {
        return Login(passwordAuthenticationService, userRepository)
    }

    @Bean
    fun passwordAuthenticationService(passwordEncodingService: PasswordEncodingService): PasswordAuthenticationService {
        return DefaultPasswordAuthenticationService(passwordEncodingService)
    }

    @Bean
    fun passwordEncodingService(): PasswordEncodingService {
        return Sha256PasswordEncodingService()
    }

    @Bean
    fun userRepository(userDao: UserDao): UserRepository {
        return JpaUserRepository(userDao)
    }
}
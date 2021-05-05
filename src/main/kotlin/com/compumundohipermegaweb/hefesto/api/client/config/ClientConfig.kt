package com.compumundohipermegaweb.hefesto.api.client.config

import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.SpringDataClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {

    @Bean
    fun clientController(registerClient: RegisterClient): ClientController {
        return ClientController(registerClient)
    }

    @Bean
    fun registerClient(jpaClientRepository: JpaClientRepository): RegisterClient {
        return RegisterClient(jpaClientRepository)
    }

    @Bean
    fun jpaClientRepository(springDataClientRepository: SpringDataClientRepository): JpaClientRepository {
        return JpaClientRepository(springDataClientRepository)
    }
}
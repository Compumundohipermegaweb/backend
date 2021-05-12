package com.compumundohipermegaweb.hefesto.api.client.config

import com.compumundohipermegaweb.hefesto.api.client.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.DefaultClientService
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.SpringDataClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {

    @Bean
    fun registerClient(clientService: ClientService): RegisterClient {
        return RegisterClient(clientService)
    }

    @Bean
    fun clientService(clientRepository: ClientRepository): ClientService {
        return DefaultClientService(clientRepository);
    }

    @Bean
    fun jpaClientRepository(springDataClientRepository: SpringDataClientRepository): ClientRepository {
        return JpaClientRepository(springDataClientRepository)
    }
}
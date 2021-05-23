package com.compumundohipermegaweb.hefesto.api.client.config

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.action.FindClients
import com.compumundohipermegaweb.hefesto.api.client.domain.action.GetBalanceByClientId
import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.DefaultClientService
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.SpringDataClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {

    @Bean
    fun registerClient(clientService: ClientService): RegisterClient {
        return RegisterClient(clientService)
    }

    @Bean
    fun findClients(jpaClientRepository: ClientRepository): FindClients {
        return FindClients(jpaClientRepository)
    }

    @Bean
    fun clientService(jpaClientRepository: ClientRepository): ClientService {
        return DefaultClientService(jpaClientRepository)
    }

    @Bean
    fun jpaClientRepository(springDataClientRepository: SpringDataClientRepository): ClientRepository {
        return JpaClientRepository(springDataClientRepository)
    }

    @Bean
    fun getBalanceByClientId(checkingAccountService: CheckingAccountService,clientService: ClientService): GetBalanceByClientId{
        return GetBalanceByClientId(checkingAccountService,clientService)
    }
}
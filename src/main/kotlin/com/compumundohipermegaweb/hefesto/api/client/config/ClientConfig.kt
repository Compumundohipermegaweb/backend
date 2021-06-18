package com.compumundohipermegaweb.hefesto.api.client.config

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.action.*
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.DefaultClientService
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.SpringDataClientRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetAllItems
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {

    @Bean
    fun registerClient(clientService: ClientService): RegisterClient {
        return RegisterClient(clientService)
    }

    @Bean
    fun updateClient(clientService: ClientService): UpdateClient {
        return UpdateClient(clientService)
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

    @Bean
    fun getAllClients(clientService: ClientService): GetAllClients {
        return GetAllClients(clientService)
    }
}
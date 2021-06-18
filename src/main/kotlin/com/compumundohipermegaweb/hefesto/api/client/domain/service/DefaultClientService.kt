package com.compumundohipermegaweb.hefesto.api.client.domain.service

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item

class DefaultClientService(private val clientRepository: ClientRepository): ClientService {
    override fun save(client: Client): Client {
        return clientRepository.save(client)
    }

    override fun findById(id: Long): Client? {
        return clientRepository.findById(id)
    }

    override fun findByDocument(documentNumber: String): Client? {
        return clientRepository.findByDocument(documentNumber)
    }

    override fun findAllClients(): List<Client> {
        return clientRepository.findAllClients()
    }
}
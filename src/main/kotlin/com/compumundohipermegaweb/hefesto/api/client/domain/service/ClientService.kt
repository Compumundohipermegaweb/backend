package com.compumundohipermegaweb.hefesto.api.client.domain.service

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client

interface ClientService {
    fun save(client: Client): Client
    fun findById(id: Long): Client?
    fun findByDocument(documentNumber: String): Client?
    fun findAllClients():List<Client>
}
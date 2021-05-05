package com.compumundohipermegaweb.hefesto.api.client.domain.model

interface ClientRepository {
    fun save(client: Client): Client
}
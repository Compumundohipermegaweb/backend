package com.compumundohipermegaweb.hefesto.api.client.domain.repository

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client

interface ClientRepository {
    fun save(client: Client): Client
    fun findByFirstNameOrLastNameIn(names: List<String>): List<Client>
    fun findByDocument(document: String): Client?
}
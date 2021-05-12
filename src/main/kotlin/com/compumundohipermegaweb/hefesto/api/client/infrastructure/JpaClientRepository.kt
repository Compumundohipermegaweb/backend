package com.compumundohipermegaweb.hefesto.api.client.infrastructure

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository

class JpaClientRepository(private val repository: SpringDataClientRepository): ClientRepository {

    override fun save(client: Client): Client {
        return repository.save(client.toDao()).toClient()
    }

    private fun ClientDao.toClient(): Client {
        return Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }

    private fun Client.toDao(): ClientDao {
        return ClientDao(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}
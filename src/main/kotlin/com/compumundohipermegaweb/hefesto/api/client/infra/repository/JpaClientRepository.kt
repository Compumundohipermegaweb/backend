package com.compumundohipermegaweb.hefesto.api.client.infra.repository

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientRepresentation

class JpaClientRepository(private val repository: SpringDataClientRepository): ClientRepository {

    override fun save(client: Client): Client {
        return repository.save(client.toDao()).toClient()
    }

    override fun findByFirstNameOrLastNameIn(names: List<String>): List<Client> {
        return repository.findAllByFirstOrLastName(names).map { it.toClient() }
    }

    override fun findByDocument(document: String): Client? {
        return repository.findByDocumentNumber(document)?.toClient()
    }

    override fun findById(id: Long): Client? {
        val client = repository.findById(id)
        if(client.isPresent){
            return client.get().toClient()
        }
        return null
    }

    override fun findAllClients(): List<Client> {
        return repository.findAll().map { it.toClient() }
    }

    private fun ClientRepresentation.toClient(): Client {
        return Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
    }

    private fun Client.toDao(): ClientRepresentation {
        if(address == null) {
            address = ""
        }
        return ClientRepresentation(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address!!)
    }


}
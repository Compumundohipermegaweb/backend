package com.compumundohipermegaweb.hefesto.api.client.infrastructure

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.model.ClientRepository
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

class JpaClientRepository(private val repository: SpringDataClientRepository): ClientRepository {

    override fun save(client: Client): Client {
        return repository.save(client.toDao()).toClient()
    }

    private fun ClientDao.toClient(): Client {
        return Client(id, documentNumber, firstName, lastName, surName, category, email, contactNumber)
    }

    private fun Client.toDao(): ClientDao {
        return ClientDao(id, documentNumber, firstName, lastName, surName, category, email, contactNumber, LocalDateTime.now(), "JONATAN", LocalDateTime.now(), "JONATAN")
    }
}
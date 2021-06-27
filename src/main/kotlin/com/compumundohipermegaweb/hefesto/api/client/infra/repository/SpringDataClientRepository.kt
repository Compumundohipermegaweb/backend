package com.compumundohipermegaweb.hefesto.api.client.infra.repository

import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataClientRepository: CrudRepository<ClientRepresentation, Long> {

    @Query("select c from ClientRepresentation c where c.firstName in :names or c.lastName in :names")
    fun findAllByFirstOrLastName(names: List<String>): List<ClientRepresentation>

    fun findByDocumentNumber(documentNumber: String): ClientRepresentation?
}
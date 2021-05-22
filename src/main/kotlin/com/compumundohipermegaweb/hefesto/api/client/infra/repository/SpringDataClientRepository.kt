package com.compumundohipermegaweb.hefesto.api.client.infra.repository

import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientDao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataClientRepository: CrudRepository<ClientDao, Long> {

    @Query("select c from ClientDao c where c.firstName in :names or c.lastName in :names")
    fun findAllByFirstOrLastName(names: List<String>): List<ClientDao>

    fun findByDocumentNumber(documentNumber: String): ClientDao?
}
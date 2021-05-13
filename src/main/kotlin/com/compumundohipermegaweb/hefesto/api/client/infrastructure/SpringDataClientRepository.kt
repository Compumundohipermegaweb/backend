package com.compumundohipermegaweb.hefesto.api.client.infrastructure

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataClientRepository: CrudRepository<ClientDao, Long> {

    @Query("select c from ClientDao c where c.firstName in :names or c.lastName in :names")
    fun findAllByFirstOrLastName(names: List<String>): List<ClientDao>

    fun findByDocumentNumber(documentNumber: String): ClientDao?
}
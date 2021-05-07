package com.compumundohipermegaweb.hefesto.api.client.infrastructure

import org.springframework.data.repository.CrudRepository

interface SpringDataClientRepository: CrudRepository<ClientDao, Long>
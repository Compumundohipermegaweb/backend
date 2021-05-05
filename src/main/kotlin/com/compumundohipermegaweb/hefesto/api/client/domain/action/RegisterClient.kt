package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.model.ClientRepository

class RegisterClient(private val clientRepository: ClientRepository) {
    operator fun invoke(client: Client) = clientRepository.save(client)
}

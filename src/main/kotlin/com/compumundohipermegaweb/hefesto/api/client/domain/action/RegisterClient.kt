package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest

class RegisterClient(private val clientService: ClientService) {
    operator fun invoke(client: ClientRequest) = clientService.save(client.toClient())

    private fun ClientRequest.toClient(): Client {
        return Client(0L, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
    }

}



package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService

class GetAllClients (private val clientService: ClientService) {
        operator fun invoke(): List<Client> = clientService.findAllClients()
}
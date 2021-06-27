package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ActionData

class FindClients(private val clientRepository: ClientRepository) {

    operator fun invoke(actionData: ActionData): List<Client> {
        var clients = clientRepository.findAllClients()

        if(!actionData.document.isNullOrBlank()) {
            clients = clients.filter { it.documentNumber == actionData.document }
        }

        if(!actionData.lastName.isNullOrBlank()) {
            clients = clients.filter { it.lastName.contains(actionData.lastName.toLowerCase().capitalize()) }
        }

        if(!actionData.name.isNullOrBlank()) {
            clients = clients.filter { it.firstName.contains(actionData.name.toLowerCase().capitalize()) }
        }

        return clients
    }

}

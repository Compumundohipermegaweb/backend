package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ActionData

class FindClients(private val clientRepository: ClientRepository) {

    operator fun invoke(actionData: ActionData): List<Client> {
        val names = parseName(actionData).filterNot { it.isBlank() }
        val document = actionData.document
        return when {
            !document.isNullOrBlank() && names.isNotEmpty() -> {
                clientRepository.findByFirstNameOrLastNameIn(names).filter { it.documentNumber == document }
            }
            !document.isNullOrBlank() -> {
                val client = clientRepository.findByDocument(document)
                if (client == null) { emptyList() } else listOf(client)
            }
            else-> clientRepository.findByFirstNameOrLastNameIn(names)
        }


    }

    private fun parseName(actionData: ActionData) = actionData.name?.split(" ") ?: emptyList()

}

package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.ActionData

class GetClient(private val clientRepository: ClientRepository) {

    operator fun invoke(actionData: ActionData): List<Client> {
        val names = parseName(actionData)
        val document = actionData.document
        return when {
            document != null && names.isNotEmpty() -> {
                clientRepository.findByFirstNameOrLastNameIn(names).filter { it.documentNumber == document }
            }
            document != null -> clientRepository.findByDocument(document)
            else-> clientRepository.findByFirstNameOrLastNameIn(names)
        }


    }

    private fun parseName(actionData: ActionData) = actionData.name?.split(" ") ?: emptyList()

}

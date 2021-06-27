package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientRepresentation
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest


class UpdateClient (private val clientService: ClientService){

    operator fun invoke(clientRequest: ClientRequest): Client? {
        val clientFromDB = clientService.findById(clientRequest.id)

        return if (clientFromDB != null) {
            val client = clientRequest.toClientDao(clientFromDB)
            clientService.save(client.toClient())
        }else{
            null
        }
    }
    private fun ClientRequest.toClientDao(clientFromDB: Client) = ClientRepresentation(id,documentNumber,firstName,lastName,state,creditLimit,email,contactNumber, address?:clientFromDB.address)
    private fun ClientRepresentation.toClient() = Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
}
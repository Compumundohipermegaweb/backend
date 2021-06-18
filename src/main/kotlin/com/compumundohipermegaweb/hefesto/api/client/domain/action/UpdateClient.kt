package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientDao
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest


class UpdateClient (private val clientService: ClientService){

    operator fun invoke(clientRequest: ClientRequest): Client? {
        val clientFromDB = clientService.findById(clientRequest.id)

        return if (clientFromDB != null) {
            val client = clientRequest.toClientDao()
            clientService.save(client.toClient())
        }else{
            null
        }
    }
    private fun ClientRequest.toClientDao() = ClientDao(id,documentNumber,firstName,lastName,state,creditLimit,email,contactNumber, address)
    private fun ClientDao.toClient() = Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
}
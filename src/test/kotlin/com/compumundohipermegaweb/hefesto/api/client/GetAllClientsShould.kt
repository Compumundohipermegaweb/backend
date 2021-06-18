package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.client.domain.action.GetAllClients
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllClientsShould {

    private lateinit var clientService: ClientService
    private lateinit var getAllClients: GetAllClients

    private lateinit var allClients: List<Client>

    @Test
    fun `find all clients`() {
        givenClientService()
        givenGetAllClients()
        whenFindingAllClients()
        thenAllClientsFound()
    }

    @Test
    fun `return all clients`() {
        givenClientService()
        givenGetAllClients()
        whenFindingAllClients()
        thenAllItemsFoundReturned()
    }

    private fun givenClientService() {
        clientService = mock()
        `when`(clientService.findAllClients()).thenReturn(ALL_CLIENTS)
    }

    private fun givenGetAllClients() {
        getAllClients = GetAllClients(clientService)
    }

    private fun whenFindingAllClients() {
        allClients = getAllClients.invoke()
    }

    private fun thenAllClientsFound() {
        verify(clientService).findAllClients()
        BDDAssertions.then(allClients).isNotNull
    }

    private fun thenAllItemsFoundReturned() {
        BDDAssertions.then(allClients).isEqualTo(ALL_CLIENTS)
    }

    private companion object {
        val ALL_CLIENTS = listOf(
            Client(0L,"1","","","",0.0,"","",null),
            Client(1L,"2","","","",0.0,"","",null),
            Client(2L,"3","","","",0.0,"","",null),
            Client(3L,"4","","","",0.0,"","",null)
        )
    }
}
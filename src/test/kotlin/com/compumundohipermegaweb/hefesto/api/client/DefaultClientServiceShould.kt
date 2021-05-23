package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.DefaultClientService
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class DefaultClientServiceShould {
    private lateinit var clientRepository: ClientRepository
    private lateinit var clientService: ClientService
    private lateinit var clientSaved: Client
    private var clientFound: Client? = null

    @Test
    fun `save the client`() {
        givenClientRepository()
        givenClientService()

        whenSavingTheClient()

        thenInputSaved()
    }

    @Test
    fun `find client by id`() {
        givenClientRepository()
        givenClientService()

        whenFindingClientById(CLIENT.id)

        thenClientHasBeenFoundById()
    }

    @Test
    fun `return the found client by id`() {
        givenClientRepository()
        givenClientService()

        whenFindingClientById(CLIENT.id)

        thenTheFoundClientIsReturned()
    }

    @Test
    fun `not find client by id`() {
        givenClientRepository()
        givenClientService()

        whenFindingClientById(1L)

        thenClientHasNotFoundById()
    }

    private fun givenClientRepository() {
        clientRepository = Mockito.mock(ClientRepository::class.java)
        `when`(clientRepository.save(CLIENT)).thenReturn(CLIENT)
        `when`(clientRepository.findById(CLIENT.id)).thenReturn(CLIENT)
        `when`(clientRepository.findById(1L)).thenReturn(null)
    }

    private fun givenClientService() {
        clientService = DefaultClientService(clientRepository)
    }

    private fun whenSavingTheClient() {
        clientSaved = clientService.save(CLIENT)
    }

    private fun whenFindingClientById(clientId: Long) {
        clientFound = clientService.findById(clientId)
    }

    private fun thenInputSaved() {
        verify(clientRepository).save(CLIENT)
        then(clientSaved).isNotNull
    }

    private fun thenClientHasBeenFoundById() {
        verify(clientRepository).findById(CLIENT.id)
        then(clientFound).isNotNull
    }

    private fun thenClientHasNotFoundById() {
        verify(clientRepository).findById(1L)
        then(clientFound).isNull()
    }

    private fun thenTheFoundClientIsReturned() {
        then(clientFound).isEqualTo(CLIENT)
    }

    private companion object {
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
    }
}
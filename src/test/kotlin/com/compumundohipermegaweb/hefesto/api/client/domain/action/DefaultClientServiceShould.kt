package com.compumundohipermegaweb.hefesto.api.client.domain.action

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

    @Test
    fun `save the client`() {
        givenClientRepository()
        givenClientService()

        whenSavingTheClient()

        thenInputSaved()
    }

    private fun givenClientRepository() {
        clientRepository = Mockito.mock(ClientRepository::class.java)
        `when`(clientRepository.save(CLIENT)).thenReturn(CLIENT)
    }

    private fun givenClientService() {
        clientService = DefaultClientService(clientRepository)
    }

    private fun whenSavingTheClient() {
        clientSaved = clientService.save(CLIENT)
    }


    private fun thenInputSaved() {
        verify(clientRepository).save(CLIENT)
        then(clientSaved).isNotNull
    }

    private companion object {
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "")
    }
}
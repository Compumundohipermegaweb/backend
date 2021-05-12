package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.ClientDao
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infrastructure.SpringDataClientRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaClientRepositoryShould {

    private lateinit var springDataClientRepository: SpringDataClientRepository
    private lateinit var clientRepository: ClientRepository
    private lateinit var clientSaved: Client

    @Test
    fun `save the client`() {
        givenClientCrudRepository()
        givenClientRepository()

        whenSavingTheClient()

        thenInputSaved()
    }

    private fun givenClientCrudRepository() {
        springDataClientRepository = mock(SpringDataClientRepository::class.java)
        `when`(springDataClientRepository.save(CLIENT_DAO)).thenReturn(CLIENT_DAO)
    }

    private fun givenClientRepository() {
        clientRepository = JpaClientRepository(springDataClientRepository)
    }

    private fun whenSavingTheClient() {
        clientSaved = clientRepository.save(CLIENT)
    }


    private fun thenInputSaved() {
        verify(springDataClientRepository).save(Mockito.any())
        then(clientSaved).isNotNull
    }

    private companion object {
        val CLIENT_DAO = ClientDao(0L, "", "", "", "", 0.0, "", "")
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "")
    }
}
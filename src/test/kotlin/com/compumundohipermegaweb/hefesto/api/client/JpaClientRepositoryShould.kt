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
    private var clientFound: Client? = null
    private lateinit var clientsFound: List<Client>

    @Test
    fun `save the client`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenSavingTheClient()

        thenInputSaved()
    }

    @Test
    fun `find client by name`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientsByName()

        thenClientsWhereFound()
    }

    @Test
    fun `find client by document`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientByDocumentNumber()

        thenClientHasBeenFound()
    }

    private fun givenSpringDataClientRepository() {
        springDataClientRepository = mock(SpringDataClientRepository::class.java)
        `when`(springDataClientRepository.save(CLIENT_DAO)).thenReturn(CLIENT_DAO)
        `when`(springDataClientRepository.findAllByFirstOrLastName(listOf(CLIENT.firstName, CLIENT.lastName))).thenReturn(listOf(CLIENT_DAO))
        `when`(springDataClientRepository.findByDocumentNumber(CLIENT.documentNumber)).thenReturn(CLIENT_DAO)
    }

    private fun givenClientRepository() {
        clientRepository = JpaClientRepository(springDataClientRepository)
    }

    private fun whenSavingTheClient() {
        clientSaved = clientRepository.save(CLIENT)
    }

    private fun whenFindingClientsByName() {
        clientsFound = clientRepository.findByFirstNameOrLastNameIn(listOf(CLIENT.firstName, CLIENT.lastName))
    }

    private fun whenFindingClientByDocumentNumber() {
        clientFound = clientRepository.findByDocument(CLIENT.documentNumber)
    }

    private fun thenInputSaved() {
        verify(springDataClientRepository).save(Mockito.any())
        then(clientSaved).isNotNull
    }

    private fun thenClientsWhereFound() {
        then(clientsFound).contains(CLIENT)
    }

    private fun thenClientHasBeenFound() {
        then(clientFound).isEqualTo(CLIENT)
    }

    private companion object {
        val CLIENT_DAO = ClientDao(0L, "00000000", "First", "Last", "", 0.0, "", "")
        val CLIENT = Client(0L, "00000000", "First", "Last", "", 0.0, "", "")
    }
}
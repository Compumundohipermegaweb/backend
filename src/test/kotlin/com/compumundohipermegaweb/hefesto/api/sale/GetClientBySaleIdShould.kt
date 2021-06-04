package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.GetClientBySaleId
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetClientBySaleIdShould {

    private lateinit var saleRepository: SaleRepository
    private lateinit var clientRepository: ClientRepository

    private lateinit var getClientBySaleId: GetClientBySaleId

    private var clientFound: Client? = null

    @Test
    fun `get client by sale id`() {
        givenSaleRepository()
        givenClientRepository()
        givenGetClientBySaleId()

        whenFindingSaleClientBySaleId()

        thenTheClientHasFound()
    }

    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.findById(SALE_DAO.id)).thenReturn(SALE_DAO)
    }

    private fun givenClientRepository() {
        clientRepository = mock()
        `when`(clientRepository.findById(SALE_DAO.clientId)).thenReturn(CLIENT)
    }

    private fun givenGetClientBySaleId() {
        getClientBySaleId = GetClientBySaleId(saleRepository, clientRepository)
    }

    private fun whenFindingSaleClientBySaleId() {
        clientFound = getClientBySaleId.invoke(SALE_DAO.id)
    }

    private fun thenTheClientHasFound() {
        verify(saleRepository).findById(SALE_DAO.id)
        verify(clientRepository).findById(SALE_DAO.clientId)
        then(clientFound).isEqualTo(CLIENT)
    }

    private companion object {
        val CLIENT = Client(0L, "00000000", "First", "Last", "", 0.0, "", "", "")
        val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, 0L, 0.0, "")
    }
}
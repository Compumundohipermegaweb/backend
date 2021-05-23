package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSaleClient
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaSaleRepositoryShould {
    private lateinit var springDataSaleClient: SpringDataSaleClient
    private lateinit var saleRepository: JpaSaleRepository
    private lateinit var savedSale: Sale

    @Test
    fun `save the input`() {
        givenSaleCrudRepository()
        givenSaleRepository()

        whenSavingTheSale()

        thenInputSaved()
    }

    private fun givenSaleCrudRepository() {
        springDataSaleClient = mock(SpringDataSaleClient::class.java)
        `when`(springDataSaleClient.save(SALE_DAO)).thenReturn(SALE_DAO)
    }

    private fun givenSaleRepository() {
        saleRepository = JpaSaleRepository(springDataSaleClient)
    }

    private fun whenSavingTheSale() {
        savedSale = saleRepository.save(SALE, INVOICE_ID)
    }

    private fun thenInputSaved() {
        verify(springDataSaleClient).save(SALE_DAO)
        then(savedSale).isNotNull
    }

    private companion object {
        const val INVOICE_ID = 0L
        val CLIENT = Client(0L, "00000000", "First", "Last", "", 0.0, "", "")
        val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, INVOICE_ID, 0.0, "")
        val SALE = Sale(0L, "B", CLIENT, 0L, 0L, SaleDetails(ArrayList(), ArrayList()), 0.0, "")

    }
}
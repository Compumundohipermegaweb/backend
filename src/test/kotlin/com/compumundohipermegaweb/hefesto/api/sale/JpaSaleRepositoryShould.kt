package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSale
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaSaleRepositoryShould {
    private lateinit var springDataSale: SpringDataSale
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
        springDataSale = mock(SpringDataSale::class.java)
        `when`(springDataSale.save(SALE_DAO)).thenReturn(SALE_DAO)
    }

    private fun givenSaleRepository() {
        saleRepository = JpaSaleRepository(springDataSale)
    }

    private fun whenSavingTheSale() {
        savedSale = saleRepository.save(SALE)
    }

    private fun thenInputSaved() {
        verify(springDataSale).save(SALE_DAO)
        then(savedSale).isNotNull
    }

    private companion object {
        val SALE_DAO = SaleDao(0L, "B", 0L, 0L, 0L, 0.0)
        val SALE = Sale(0L, "B", 0L, 0L, 0L, SaleDetails(ArrayList(), ArrayList()), 0.0)

    }
}
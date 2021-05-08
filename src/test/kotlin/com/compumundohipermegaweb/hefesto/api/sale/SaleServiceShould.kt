package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailsRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SaleServiceShould {

    private lateinit var saleRepository: SaleRepository
    private lateinit var saleDetailsRepository: SaleDetailsRepository
    private lateinit var saleService: SaleService
    private lateinit var resultado: Sale

    @Test
    fun `return a sale object`() {
        givenSaleDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheResult()

        thenResultIsTheExpectedOne()
    }

    private fun givenSaleDetailRepository() {
        saleDetailsRepository = mock(SaleDetailsRepository::class.java)
        `when`(saleDetailsRepository.save(SALE_DETAIL, 0L)).thenReturn(SALE_DETAIL)
    }

    private fun givenSaleRepository() {
        saleRepository = mock(SaleRepository::class.java)
        `when`(saleRepository.save(SALE)).thenReturn(SALE)
    }

    private fun givenSaleService() {
        saleService = SaleService(saleRepository, saleDetailsRepository)
    }

    private fun whenSavingTheResult() {
        resultado = saleService.save(SALE)
    }

    private fun thenResultIsTheExpectedOne() {
        then(resultado).isEqualTo(SALE)
    }

    private companion object {
        val SALE_DETAIL = SaleDetail(0L, 0, 0.0)
        val SALE = Sale(0L, "B", 0L, 0L, 0L, SaleDetails(ArrayList()), 0.0)
    }
}
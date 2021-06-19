package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSaleDetailClient
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class JpaSaleDetailRepositoryShould {
    private lateinit var springDataSaleItemDetail: SpringDataSaleDetailClient
    private lateinit var saleDetailRepository: SaleDetailRepository
    private lateinit var savedSaleDetail: SaleDetail
    private lateinit var saleDetailsFound: List<SaleDetail>

    @Test
    fun `save the input`() {
        givenSaleDetailCrudRepository()
        givenSaleDetailRepository()

        whenSavingTheSaleDetail()

        thenInputSaved()
    }

    @Test
    fun `get sale details by sale id`() {
        givenSaleDetailCrudRepository()
        givenSaleDetailRepository()

        whenFindingSaleDetailsBySaleId()

        thenTheSaleDetailsAreFound()
    }

    private fun givenSaleDetailCrudRepository() {
        springDataSaleItemDetail = mock()
        `when`(springDataSaleItemDetail.save(SALE_DETAIL_DAO)).thenReturn(SALE_DETAIL_DAO)
        `when`(springDataSaleItemDetail.findBySaleId(0L)).thenReturn(listOf(SALE_DETAIL_DAO))
    }

    private fun givenSaleDetailRepository() {
        saleDetailRepository = JpaSaleDetailRepository(springDataSaleItemDetail)
    }

    private fun whenSavingTheSaleDetail() {
        savedSaleDetail = saleDetailRepository.save(SALE_DETAIL, 0L)
    }

    private fun whenFindingSaleDetailsBySaleId() {
        saleDetailsFound = saleDetailRepository.findBySaleId(0L)
    }

    private fun thenInputSaved() {
        verify(springDataSaleItemDetail).save(SALE_DETAIL_DAO)
        then(savedSaleDetail).isNotNull
    }

    private fun thenTheSaleDetailsAreFound() {
        verify(springDataSaleItemDetail).findBySaleId(0L)
        then(saleDetailsFound).isEqualTo(listOf(SALE_DETAIL))
    }

    private companion object {
        val SALE_DETAIL_DAO = SaleDetailDao(0L, "", "", 0L, 0, 0.0)
        val SALE_DETAIL = SaleDetail(0L, "", "", 0, 0.0)
    }
}
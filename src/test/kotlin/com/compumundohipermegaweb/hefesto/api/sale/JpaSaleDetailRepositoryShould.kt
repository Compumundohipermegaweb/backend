package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailsRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaSaleDetailRepositoryShould {
    private lateinit var springDataSaleDetail: SpringDataSaleDetail
    private lateinit var saleDetailsRepository: SaleDetailsRepository
    private lateinit var savedSaleDetail: SaleDetail

    @Test
    fun `save the input`() {
        givenSaleDetailCrudRepository()
        givenSaleDetailRepository()

        whenSavingTheSaleDetail()

        thenInputSaved()
    }

    private fun givenSaleDetailCrudRepository() {
        springDataSaleDetail = mock(SpringDataSaleDetail::class.java)
        `when`(springDataSaleDetail.save(SALE_DETAIL_DAO)).thenReturn(SALE_DETAIL_DAO)
    }

    private fun givenSaleDetailRepository() {
        saleDetailsRepository = JpaSaleDetailRepository(springDataSaleDetail)
    }

    private fun whenSavingTheSaleDetail() {
        savedSaleDetail = saleDetailsRepository.save(SALE_DETAIL, 0L)
    }

    private fun thenInputSaved() {
        verify(springDataSaleDetail).save(SALE_DETAIL_DAO)
        then(savedSaleDetail).isNotNull
    }

    private companion object {
        val SALE_DETAIL_DAO = SaleDetailDao(0L, 0L, 0, 0.0)
        val SALE_DETAIL = SaleDetail(0L, 0, 0.0)
    }
}
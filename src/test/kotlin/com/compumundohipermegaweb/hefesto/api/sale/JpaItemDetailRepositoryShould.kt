package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSaleItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleItemDetailDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class JpaItemDetailRepositoryShould {
    private lateinit var springDataSaleItemDetail: SpringDataSaleItemDetail
    private lateinit var saleItemDetailRepository: SaleItemDetailRepository
    private lateinit var savedItemDetail: ItemDetail

    @Test
    fun `save the input`() {
        givenSaleDetailCrudRepository()
        givenSaleDetailRepository()

        whenSavingTheSaleDetail()

        thenInputSaved()
    }

    private fun givenSaleDetailCrudRepository() {
        springDataSaleItemDetail = mock(SpringDataSaleItemDetail::class.java)
        `when`(springDataSaleItemDetail.save(SALE_DETAIL_DAO)).thenReturn(SALE_DETAIL_DAO)
    }

    private fun givenSaleDetailRepository() {
        saleItemDetailRepository = JpaSaleItemDetailRepository(springDataSaleItemDetail)
    }

    private fun whenSavingTheSaleDetail() {
        savedItemDetail = saleItemDetailRepository.save(SALE_DETAIL, 0L)
    }

    private fun thenInputSaved() {
        verify(springDataSaleItemDetail).save(SALE_DETAIL_DAO)
        then(savedItemDetail).isNotNull
    }

    private companion object {
        val SALE_DETAIL_DAO = SaleItemDetailDao(0L, "",0L, 0, 0.0)
        val SALE_DETAIL = ItemDetail(0L, "",0, 0.0)
    }
}
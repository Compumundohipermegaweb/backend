package com.compumundohipermegaweb.hefesto.api.rejected.sale

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedSaleRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.DefaultRejectedSaleService
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.RejectedSaleService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultRejectedSaleServiceShould {
    private lateinit var rejectedItemDetailRepository: RejectedItemDetailRepository
    private lateinit var rejectedSaleRepository: RejectedSaleRepository
    private lateinit var rejectedSaleService: RejectedSaleService

    private lateinit var rejectedSaleSaved: RejectedSale

    @Test
    fun `save the rejected sale`() {
        givenRejectedItemDetailRepository()
        givenRejectedSaleRepository()
        givenRejectedSaleService()

        whenSavingTheRejectedSale()

        thenTheRejectedSaleIsSaved()
    }

    @Test
    fun `return the saved rejected sale`() {
        givenRejectedItemDetailRepository()
        givenRejectedSaleRepository()
        givenRejectedSaleService()

        whenSavingTheRejectedSale()

        thenTheRejectedSaleSavedIsReturned()
    }

    private fun givenRejectedSaleRepository() {
        rejectedSaleRepository = mock()
        `when`(rejectedSaleRepository.saveRejectedSale(REJECTED_SALE)).thenReturn(EXPECTED_REJECTED_SALE)
    }

    private fun givenRejectedItemDetailRepository() {
        rejectedItemDetailRepository = mock()
        `when`(rejectedItemDetailRepository.saveRejectedItemDetail(REJECTED_ITEM_DETAIL, EXPECTED_REJECTED_SALE.id)).thenReturn(REJECTED_ITEM_DETAIL)
        `when`(rejectedItemDetailRepository.saveRejectedItemDetail(ANOTHER_REJECTED_ITEM_DETAIL, EXPECTED_REJECTED_SALE.id)).thenReturn(ANOTHER_REJECTED_ITEM_DETAIL)
    }

    private fun givenRejectedSaleService() {
        rejectedSaleService = DefaultRejectedSaleService(rejectedSaleRepository, rejectedItemDetailRepository)
    }

    private fun whenSavingTheRejectedSale() {
        rejectedSaleSaved = rejectedSaleService.saveRejectedSale(REJECTED_SALE, listOf(REJECTED_ITEM_DETAIL, ANOTHER_REJECTED_ITEM_DETAIL))
    }

    private fun thenTheRejectedSaleIsSaved() {
        verify(rejectedSaleRepository).saveRejectedSale(REJECTED_SALE)
        verify(rejectedItemDetailRepository).saveRejectedItemDetail(REJECTED_ITEM_DETAIL, EXPECTED_REJECTED_SALE.id)
        verify(rejectedItemDetailRepository).saveRejectedItemDetail(REJECTED_ITEM_DETAIL, EXPECTED_REJECTED_SALE.id)
        then(rejectedSaleSaved).isNotNull
    }

    private fun thenTheRejectedSaleSavedIsReturned() {
        then(rejectedSaleSaved).isEqualTo(EXPECTED_REJECTED_SALE)
    }

    private companion object {
        private val REJECTED_SALE = RejectedSale(0L, 0L, 0.0, "", "")
        private val REJECTED_ITEM_DETAIL = RejectedItemDetail(1L, "", "",  0, 0.0, "")
        private val ANOTHER_REJECTED_ITEM_DETAIL = RejectedItemDetail(2L, "", "",  0, 0.0, "")
        private val EXPECTED_REJECTED_SALE = RejectedSale(1L, 0L, 0.0, "", "")
    }
}
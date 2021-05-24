package com.compumundohipermegaweb.hefesto.api.rejected.sale

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.JpaRejectedSaleRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.SpringDataRejectedSaleDao
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation.RejectedSaleRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaRejectedSaleRepositoryShould {

    private lateinit var springDataRejectedSaleDao: SpringDataRejectedSaleDao
    private lateinit var rejectedSaleRepository: JpaRejectedSaleRepository

    private lateinit var rejectedSaleSaved: RejectedSale

    @Test
    fun `save the rejected sale`() {
        givenSpringDataRejectedSaleDao()
        givenRejectedSaleRepository()

        whenSavingTheRejectedSale()

        thenTheRejectedSaleIsSaved()
    }

    @Test
    fun `return the rejected sale saved`() {
        givenSpringDataRejectedSaleDao()
        givenRejectedSaleRepository()

        whenSavingTheRejectedSale()

        thenTheRejectedSaleIsReturned()
    }

    private fun givenSpringDataRejectedSaleDao() {
        springDataRejectedSaleDao = mock()
        `when`(springDataRejectedSaleDao.save(REJECTED_SALE_REPRESENTATION)).thenReturn(REJECTED_SALE_REPRESENTATION)
    }

    private fun givenRejectedSaleRepository() {
        rejectedSaleRepository = JpaRejectedSaleRepository(springDataRejectedSaleDao)
    }

    private fun whenSavingTheRejectedSale() {
        rejectedSaleSaved = rejectedSaleRepository.saveRejectedSale(REJECTED_SALE)
    }

    private fun thenTheRejectedSaleIsSaved() {
        verify(springDataRejectedSaleDao).save(REJECTED_SALE_REPRESENTATION)
        then(rejectedSaleSaved).isNotNull
    }

    private fun thenTheRejectedSaleIsReturned() {
        then(rejectedSaleSaved).isEqualTo(REJECTED_SALE)
    }

    private companion object {
        private val REJECTED_SALE_REPRESENTATION = RejectedSaleRepresentation(0L, 0L, 0.0, "", "", "")
        private val REJECTED_SALE = RejectedSale(0L, 0L, 0.0, "", "", "")
    }
}
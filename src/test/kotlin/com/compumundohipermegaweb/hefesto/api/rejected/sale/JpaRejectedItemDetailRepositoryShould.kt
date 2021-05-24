package com.compumundohipermegaweb.hefesto.api.rejected.sale

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.JpaRejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.SpringRejectedItemDetailDao
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation.RejectedItemDetailRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaRejectedItemDetailRepositoryShould {
    private lateinit var springRejectedItemDetailDao: SpringRejectedItemDetailDao
    private lateinit var rejectedItemDetailRepository: JpaRejectedItemDetailRepository

    private lateinit var rejectedItemDetailSaved: RejectedItemDetail

    @Test
    fun `save the rejected item detail`() {
        givenSpringDataRejectedItemDetailDao()
        givenRejectedItemDetailRepository()

        whenSavingTheRejectedItemDetail()

        thenTheRejectedItemDetailIsSaved()
    }

    @Test
    fun `return the rejected item detail saved`() {
        givenSpringDataRejectedItemDetailDao()
        givenRejectedItemDetailRepository()

        whenSavingTheRejectedItemDetail()

        thenTheRejectedItemDetailIsReturned()
    }

    private fun givenSpringDataRejectedItemDetailDao() {
        springRejectedItemDetailDao = mock()
        `when`(springRejectedItemDetailDao.save(REJECTED_ITEM_DETAIL_REPRESENTATION)).thenReturn(REJECTED_ITEM_DETAIL_REPRESENTATION)
    }

    private fun givenRejectedItemDetailRepository() {
        rejectedItemDetailRepository = JpaRejectedItemDetailRepository(springRejectedItemDetailDao)
    }

    private fun whenSavingTheRejectedItemDetail() {
        rejectedItemDetailSaved = rejectedItemDetailRepository.saveRejectedItemDetail(REJECTED_ITEM_DETAIL, 1L)
    }

    private fun thenTheRejectedItemDetailIsSaved() {
        verify(springRejectedItemDetailDao).save(REJECTED_ITEM_DETAIL_REPRESENTATION)
        then(rejectedItemDetailSaved).isNotNull
    }

    private fun thenTheRejectedItemDetailIsReturned() {
        then(rejectedItemDetailSaved).isEqualTo(REJECTED_ITEM_DETAIL)
    }

    private companion object {
        private val REJECTED_ITEM_DETAIL_REPRESENTATION = RejectedItemDetailRepresentation(0L, 0L, "", "", 1L, 0, 0.0, "")
        private val REJECTED_ITEM_DETAIL = RejectedItemDetail(0L, 0L, "", "",  0, 0.0, "")
    }
}
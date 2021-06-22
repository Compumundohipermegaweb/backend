package com.compumundohipermegaweb.hefesto.api.discount.infra.repository

import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount
import com.compumundohipermegaweb.hefesto.api.discount.domain.repositorty.DiscountRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaDiscountRepositoryShould {

    private lateinit var discountDao: DiscountDao
    private lateinit var discountRepository: DiscountRepository

    @Test
    fun `save the discount`() {
        givenDiscountDao()
        givenDiscountRepository()

        whenSavingDiscount()

        thenDiscountIsSaved()
    }

    private fun givenDiscountDao() {
        discountDao = mock()
        `when`(discountDao.save(DISCOUNT_REPRESENTATION)).thenReturn(DISCOUNT_REPRESENTATION)
    }

    private fun givenDiscountRepository() {
        discountRepository = JpaDiscountRepository(discountDao)
    }

    private fun whenSavingDiscount() {
        discountRepository.save(DISCOUNT)
    }

    private fun thenDiscountIsSaved() {
        verify(discountDao).save(any())
    }

    private companion object {
        val DISCOUNT = Discount(0L, 1, 1.0, 1L)
        val DISCOUNT_REPRESENTATION = DiscountRepresentation(0L, DISCOUNT.percentage, DISCOUNT.amount, DISCOUNT.saleId)
    }
}
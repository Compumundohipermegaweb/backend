package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PriceToleranceRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaPriceToleranceRepositoryShould {

    private lateinit var priceToleranceDao: PriceToleranceDao
    private lateinit var priceToleranceRepository: PriceToleranceRepository

    private var tolerance: Int = 0

    @Test
    fun `find the price tolerance percentage`() {
        givenPriceToleranceDao()
        givenPriceToleranceExists()
        givenPriceToleranceRepository()

        whenFindingTolerance()

        verify(priceToleranceDao).findAll()
        then(tolerance).isEqualTo(10)
    }

    @Test
    fun `return default tolerance if is not defined`() {
        givenPriceToleranceDao()
        givenPriceToleranceRepository()

        whenFindingTolerance()

        then(tolerance).isEqualTo(DEFAULT_TOLERANCE)
    }

    private fun givenPriceToleranceDao() {
        priceToleranceDao = mock()
    }

    private fun givenPriceToleranceExists() {
        `when`(priceToleranceDao.findAll()).thenReturn(listOf(PriceToleranceRepresentation(1L, 10)))
    }

    private fun givenPriceToleranceRepository() {
        priceToleranceRepository = JpaPriceToleranceRepository(priceToleranceDao)
    }

    private fun whenFindingTolerance() {
        tolerance = priceToleranceRepository.find()
    }

    private companion object {
        const val DEFAULT_TOLERANCE = 5
    }
}
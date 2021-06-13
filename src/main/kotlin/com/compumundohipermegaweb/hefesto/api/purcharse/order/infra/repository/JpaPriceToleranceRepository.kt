package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository

class JpaPriceToleranceRepository(private val priceToleranceDao: PriceToleranceDao): PriceToleranceRepository {

    override fun find(): Int {
        val representation = priceToleranceDao.findAll()
        return if (representation.none()) { DEFAULT_TOLERANCE } else { representation.first().tolerance}
    }

    private companion object {
        const val DEFAULT_TOLERANCE = 5
    }
}
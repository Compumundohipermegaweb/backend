package com.compumundohipermegaweb.hefesto.api.discount.infra.repository

import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount
import com.compumundohipermegaweb.hefesto.api.discount.domain.repositorty.DiscountRepository

class JpaDiscountRepository(private val discountDao: DiscountDao) : DiscountRepository {

    override fun save(discount: Discount): Discount {
        val representation = discount.toRepresentation()
        return discountDao.save(representation).toDiscount()
    }

    private fun Discount.toRepresentation() = DiscountRepresentation(id, percentage, amount, saleId)

    private fun DiscountRepresentation.toDiscount() = Discount(id, percentage, amount, saleId)
}

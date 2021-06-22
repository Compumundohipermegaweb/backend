package com.compumundohipermegaweb.hefesto.api.discount.domain.repositorty

import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount

interface DiscountRepository {
    fun save(discount: Discount): Discount
}

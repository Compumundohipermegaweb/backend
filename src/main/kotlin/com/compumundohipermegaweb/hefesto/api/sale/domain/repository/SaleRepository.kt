package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale

interface SaleRepository {
    fun save(sale: Sale): Sale
}
package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao

interface SaleRepository {
    fun save(sale: Sale, invoiceId: Long): Sale
    fun findById(saleId: Long): SaleDao?
}
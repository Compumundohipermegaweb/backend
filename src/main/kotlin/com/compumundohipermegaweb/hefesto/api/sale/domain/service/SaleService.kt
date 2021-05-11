package com.compumundohipermegaweb.hefesto.api.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale

interface SaleService {
    fun save(sale: Sale, invoiceId: Long): Sale
}
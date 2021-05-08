package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail

interface SaleDetailsRepository {
    fun save(saleDetail: SaleDetail, saleId: Long): SaleDetail
}
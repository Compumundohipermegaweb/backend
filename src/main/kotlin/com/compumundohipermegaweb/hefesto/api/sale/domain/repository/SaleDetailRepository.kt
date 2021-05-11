package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail

interface SaleDetailRepository {
    fun save(saleDetail: SaleDetail, saleId: Long): SaleDetail
    fun saveAll(itemsDetails: List<SaleDetail>, saleId: Long): List<SaleDetail>
}
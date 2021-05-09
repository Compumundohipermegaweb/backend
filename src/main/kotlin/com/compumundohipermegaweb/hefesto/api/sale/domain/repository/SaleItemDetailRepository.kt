package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail

interface SaleItemDetailRepository {
    fun save(itemDetail: ItemDetail, saleId: Long): ItemDetail
}
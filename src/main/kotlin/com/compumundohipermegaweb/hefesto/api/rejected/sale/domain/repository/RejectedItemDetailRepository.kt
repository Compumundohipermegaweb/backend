package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail

interface RejectedItemDetailRepository {
    fun saveRejectedItemDetail(rejectedItemDetail: RejectedItemDetail, rejectedSaleId: Long): RejectedItemDetail
}
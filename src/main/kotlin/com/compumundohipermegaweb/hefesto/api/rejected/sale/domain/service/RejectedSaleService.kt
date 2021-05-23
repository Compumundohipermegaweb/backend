package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale

interface RejectedSaleService {
    fun saveRejectedSale(rejectedSale: RejectedSale, rejectedItemDetails: List<RejectedItemDetail>): RejectedSale
}
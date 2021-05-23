package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale

interface RejectedSaleRepository {
    fun saveRejectedSale(rejectedSale: RejectedSale): RejectedSale
}
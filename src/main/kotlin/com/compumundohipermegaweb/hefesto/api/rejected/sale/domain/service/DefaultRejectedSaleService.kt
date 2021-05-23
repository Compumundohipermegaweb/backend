package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedSaleRepository

class DefaultRejectedSaleService(private val rejectedSaleRepository: RejectedSaleRepository,
                                 private val rejectedItemDetailRepository: RejectedItemDetailRepository): RejectedSaleService {
    override fun saveRejectedSale(rejectedSale: RejectedSale, rejectedItemDetails: List<RejectedItemDetail>): RejectedSale {
        val rejectedSaleSaved = rejectedSaleRepository.saveRejectedSale(rejectedSale)
        rejectedItemDetails.forEach { rejectedItemDetailRepository.saveRejectedItemDetail(it, rejectedSaleSaved.id) }
        return rejectedSaleSaved
    }
}
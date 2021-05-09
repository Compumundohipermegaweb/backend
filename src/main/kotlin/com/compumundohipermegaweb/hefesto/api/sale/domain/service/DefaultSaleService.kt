package com.compumundohipermegaweb.hefesto.api.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class DefaultSaleService(private val saleRepository: SaleRepository,
                         private val saleItemDetailRepository: SaleItemDetailRepository,
                         private val salePaymentDetailRepository: SalePaymentDetailRepository): SaleService {
    override fun save(sale: Sale): Sale {
        val saveSale = saleRepository.save(sale)

        sale.saleDetails.itemsDetails.forEach{saleItemDetailRepository.save(it, sale.id)}
        sale.saleDetails.paymentDetails.forEach{salePaymentDetailRepository.save(it, sale.id)}

        saveSale.saleDetails.itemsDetails = sale.saleDetails.itemsDetails
        saveSale.saleDetails.paymentDetails = sale.saleDetails.paymentDetails

        return saveSale
    }



}

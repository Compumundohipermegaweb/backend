package com.compumundohipermegaweb.hefesto.api.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class DefaultSaleService(private val saleRepository: SaleRepository,
                         private val saleDetailRepository: SaleDetailRepository,
                         private val salePaymentRepository: SalePaymentRepository): SaleService {
    override fun save(sale: Sale, invoiceId: Long): Sale {
        val savedSale = saleRepository.save(sale, invoiceId)

        savedSale.saleDetails.details = saleDetailRepository.saveAll(sale.saleDetails.details, savedSale.id)
        savedSale.saleDetails.payments = salePaymentRepository.saveAll(sale.saleDetails.payments, savedSale.id)

        return savedSale
    }



}

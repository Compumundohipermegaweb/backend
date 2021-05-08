package com.compumundohipermegaweb.hefesto.api.sale.domain.service

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailsRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class DefaultSaleService(private val saleRepository: SaleRepository,
                         private val saleDetailsRepository: SaleDetailsRepository): SaleService {
    override fun save(sale: Sale): Sale {
        val saveSale = saleRepository.save(sale)

        sale.saleDetails.itemsDetails.forEach{saleDetailsRepository.save(it, sale.id)}

        return saveSale
    }



}

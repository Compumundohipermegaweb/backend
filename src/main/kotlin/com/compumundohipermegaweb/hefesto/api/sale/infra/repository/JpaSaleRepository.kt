package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao

class JpaSaleRepository(private val springDataSaleClient: SpringDataSaleClient): SaleRepository {

    override fun save(sale: Sale, invoiceId: Long): Sale {
        val saleDao = SaleDao(sale.id, sale.type, sale.client.id, sale.salesmanId, sale.branchId, invoiceId, sale.total, sale.category)
        return springDataSaleClient.save(saleDao).toSale(sale)
    }

    override fun findById(saleId: Long): SaleDao? {
        val sale = springDataSaleClient.findById(saleId)
        if(sale.isPresent) {
            return sale.get()
        }
        return null
    }

    private fun SaleDao.toSale(sale: Sale): Sale {
        return Sale(id, type, sale.client, salesmanId, branchId,  SaleDetails(ArrayList(), ArrayList()), total, category)
    }
}
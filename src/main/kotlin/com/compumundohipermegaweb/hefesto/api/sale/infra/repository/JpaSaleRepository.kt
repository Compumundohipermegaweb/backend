package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao

class JpaSaleRepository(private val springDataSaleClient: SpringDataSaleClient): SaleRepository {

    override fun save(sale: Sale, invoiceId: Long): Sale {
        val saleDao = SaleDao(sale.id, sale.type, sale.clientId, sale.salesmanId, sale.branchId, invoiceId, sale.total)
        return springDataSaleClient.save(saleDao).toSale()
    }

    private fun SaleDao.toSale(): Sale {
        return Sale(id, type, clientId, salesmanId, branchId,  SaleDetails(ArrayList(), ArrayList()), total)
    }
}
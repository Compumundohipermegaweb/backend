package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao

class JpaSaleRepository(private val springDataSaleClient: SpringDataSaleClient): SaleRepository {

    override fun save(sale: Sale, invoiceId: Long): Sale {
        val saleDao = SaleDao(sale.id, sale.type, sale.client.id, sale.salesmanId, sale.branchId, invoiceId, sale.total)
        return springDataSaleClient.save(saleDao).toSale(sale.client)
    }

    private fun SaleDao.toSale(client: Client): Sale {
        return Sale(id, type, client, salesmanId, branchId,  SaleDetails(ArrayList(), ArrayList()), total)
    }
}
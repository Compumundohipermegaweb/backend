package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
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

    override fun findAll(): List<Sale> {
        return springDataSaleClient.findAll().map { it.toSaleForReports() }
    }

    override fun findByBranchId(branchId: Long): List<Sale> {
        return springDataSaleClient.findByBranchId(branchId).map { it.toSaleForReports() }
    }

    private fun SaleDao.toSale(sale: Sale): Sale {
        return Sale(id, type, sale.client, salesmanId, branchId,  SaleDetails(ArrayList(), ArrayList(), null), total, category)
    }

    private fun SaleDao.toSaleForReports(): Sale {
        val client = Client(0L, "", "", "", "", 0.0, "", "", "")
        return Sale(id, type, client, salesmanId, branchId, SaleDetails(emptyList(), emptyList(), null), total, category)
    }
}



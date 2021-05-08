package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao

class JpaSaleRepository(private val springDataSale: SpringDataSale): SaleRepository {

    override fun save(sale: Sale): Sale {
        return springDataSale.save(sale.toSaleDao()).toSale()
    }

    private fun Sale.toSaleDao(): SaleDao {
        return SaleDao(id, type, clientId, salesmanId, branchId, total)
    }

    private fun SaleDao.toSale(): Sale {
        return Sale(id, type, clientId, salesmanId, branchId,  SaleDetails(ArrayList()), total)
    }
}
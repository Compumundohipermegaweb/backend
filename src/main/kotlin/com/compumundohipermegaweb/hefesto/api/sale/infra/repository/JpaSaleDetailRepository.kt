package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailsRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao

class JpaSaleDetailRepository(private val springDataSaleDetail: SpringDataSaleDetail): SaleDetailsRepository {

    override fun save(saleDetail: SaleDetail, saleId: Long): SaleDetail {
        return springDataSaleDetail.save(saleDetail.toSaleDetailDao(saleId)).toSaleDetail()
    }

    private fun SaleDetail.toSaleDetailDao(saleId: Long): SaleDetailDao {
        return SaleDetailDao(id, saleId, quantity, unitPrice)
    }

    private fun SaleDetailDao.toSaleDetail(): SaleDetail {
        return SaleDetail(id, quantity, unitPrice)
    }
}



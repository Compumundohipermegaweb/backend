package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao

class JpaSaleDetailRepository(private val springDataSaleItemDetail: SpringDataSaleDetailClient): SaleDetailRepository {

    override fun save(saleDetail: SaleDetail, saleId: Long): SaleDetail {
        return springDataSaleItemDetail.save(saleDetail.toSaleDetailDao(saleId)).toSaleDetail()
    }

    override fun saveAll(itemsDetails: List<SaleDetail>, saleId: Long): List<SaleDetail> {
        val saleDetailsDao = itemsDetails.map { it.toSaleDetailDao(saleId)}
        return springDataSaleItemDetail.saveAll(saleDetailsDao).map { it.toSaleDetail() }
    }

    override fun findBySaleId(saleId: Long): List<SaleDetail> {
        return springDataSaleItemDetail.findBySaleId(saleId).map { it.toSaleDetail() }
    }

    private fun SaleDetail.toSaleDetailDao(saleId: Long): SaleDetailDao {
        return SaleDetailDao(id, sku, description, saleId, quantity, unitPrice)
    }

    private fun SaleDetailDao.toSaleDetail(): SaleDetail {
        return SaleDetail(id, sku, description, quantity, unitPrice)
    }
}



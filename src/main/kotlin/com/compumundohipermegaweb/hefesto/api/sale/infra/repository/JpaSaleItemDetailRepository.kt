package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleItemDetailDao

class JpaSaleItemDetailRepository(private val springDataSaleItemDetail: SpringDataSaleItemDetail): SaleItemDetailRepository {

    override fun save(itemDetail: ItemDetail, saleId: Long): ItemDetail {
        return springDataSaleItemDetail.save(itemDetail.toSaleDetailDao(saleId)).toSaleDetail()
    }

    private fun ItemDetail.toSaleDetailDao(saleId: Long): SaleItemDetailDao {
        return SaleItemDetailDao(id, description, saleId, quantity, unitPrice)
    }

    private fun SaleItemDetailDao.toSaleDetail(): ItemDetail {
        return ItemDetail(id, description, quantity, unitPrice)
    }
}



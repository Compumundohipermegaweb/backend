package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDetailDao

class JpaSalePaymentDetailRepository(private val springDataSalePaymentDetail: SpringDataSalePaymentDetail): SalePaymentDetailRepository {
    override fun save(paymentDetail: PaymentDetail, saleId: Long): PaymentDetail {
        return springDataSalePaymentDetail.save(paymentDetail.toPaymentDetailDao(saleId)).toPaymentDetail()
    }

    private fun PaymentDetail.toPaymentDetailDao(saleId: Long): SalePaymentDetailDao {
        return SalePaymentDetailDao(id, saleId, type, subTotal)
    }

    private fun SalePaymentDetailDao.toPaymentDetail(): PaymentDetail {
        return PaymentDetail(id, type, subTotal)
    }
}





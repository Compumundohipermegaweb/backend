package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail

interface SalePaymentDetailRepository {
    fun save(paymentDetail: PaymentDetail, saleId: Long): PaymentDetail
}
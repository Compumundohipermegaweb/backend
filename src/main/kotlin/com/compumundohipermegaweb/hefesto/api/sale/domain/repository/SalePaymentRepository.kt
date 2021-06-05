package com.compumundohipermegaweb.hefesto.api.sale.domain.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment

interface SalePaymentRepository {
    fun save(payments: SalePayment, saleId: Long): SalePayment
    fun saveAll(payments: List<SalePayment>, saleId: Long): List<SalePayment>
    fun findBySaleId(saleID: Long): List<SalePayment>
    fun delete(salePayment: SalePayment, saleId: Long)
}
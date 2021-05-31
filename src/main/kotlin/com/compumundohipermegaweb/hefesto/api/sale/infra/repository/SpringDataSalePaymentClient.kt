package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSalePaymentClient: CrudRepository<SalePaymentDao, Long> {
    fun findBySaleId(saleId: Long): List<SalePaymentDao>
}
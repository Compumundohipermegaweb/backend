package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDetailDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSalePaymentDetail: CrudRepository<SalePaymentDetailDao, Long> {
}
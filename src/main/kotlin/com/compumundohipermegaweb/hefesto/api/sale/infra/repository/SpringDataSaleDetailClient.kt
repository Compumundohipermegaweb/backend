package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataSaleDetailClient: CrudRepository<SaleDetailDao, Long> {
    @Query(value = "SELECT * FROM SALE_DETAIL WHERE SALE_ID = ?1", nativeQuery = true)
    fun findBySaleId(saleId: Long): List<SaleDetailDao>
}
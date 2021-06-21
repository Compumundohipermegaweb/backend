package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringDataSaleClient: CrudRepository<SaleDao, Long> {
    @Query("SELECT * FROM SALE WHERE BRANCH_ID = ?1", nativeQuery = true)
    fun findByBranchId(branchId: Long): List<SaleDao>
}
package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSale: CrudRepository<SaleDao, Long> {
}
package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleItemDetailDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSaleItemDetail: CrudRepository<SaleItemDetailDao, Long>
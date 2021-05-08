package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SaleDetailDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSaleDetail: CrudRepository<SaleDetailDao, Long>
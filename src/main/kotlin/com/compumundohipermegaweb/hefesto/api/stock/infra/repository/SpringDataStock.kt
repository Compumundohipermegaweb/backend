package com.compumundohipermegaweb.hefesto.api.stock.infra.repository

import com.compumundohipermegaweb.hefesto.api.stock.infra.representation.StockDao
import org.springframework.data.repository.CrudRepository

interface SpringDataStock: CrudRepository<StockDao, Long> {
}
package com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation.RejectedSaleRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringDataRejectedSaleDao: CrudRepository<RejectedSaleRepresentation, Long>
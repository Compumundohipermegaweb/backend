package com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedSaleRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation.RejectedSaleRepresentation

class JpaRejectedSaleRepository(private val springDataRejectedSaleDao: SpringDataRejectedSaleDao): RejectedSaleRepository {
    override fun saveRejectedSale(rejectedSale: RejectedSale): RejectedSale {
        return springDataRejectedSaleDao.save(rejectedSale.toRepresentation()).toRejectedSale()
    }

    private fun RejectedSale.toRepresentation(): RejectedSaleRepresentation {
        return RejectedSaleRepresentation(id, saleId, total, category, reason)
    }

    private fun RejectedSaleRepresentation.toRejectedSale(): RejectedSale {
        return RejectedSale(id, saleId, total, category, reason)
    }
}





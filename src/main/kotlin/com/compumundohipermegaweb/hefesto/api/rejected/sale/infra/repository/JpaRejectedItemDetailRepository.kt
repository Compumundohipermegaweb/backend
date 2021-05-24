package com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation.RejectedItemDetailRepresentation

class JpaRejectedItemDetailRepository(private val springRejectedItemDetailDao: SpringRejectedItemDetailDao): RejectedItemDetailRepository {
    override fun saveRejectedItemDetail(rejectedItemDetail: RejectedItemDetail, rejectedSaleId: Long): RejectedItemDetail {
        return springRejectedItemDetailDao.save(rejectedItemDetail.toRepresentation(rejectedSaleId)).toRejectedItemDetail()
    }

    private fun RejectedItemDetail.toRepresentation(rejectedSaleId: Long): RejectedItemDetailRepresentation {
        return RejectedItemDetailRepresentation(id, itemId, sku, description, rejectedSaleId, quantity, unitPrice, reason)
    }

    private fun RejectedItemDetailRepresentation.toRejectedItemDetail(): RejectedItemDetail {
        return RejectedItemDetail(id, itemId, sku, description, quantity, unitPrice, reason)
    }

}





package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model

data class PurchaseOrder(
        val id: Long,
        val branchId: Long,
        val sku: String,
        val amount: Int,
        val supplier: String,
        val status: Status) {

    enum class Status {
        PENDING, ACCEPTED, CONFIRMED, REJECTED
    }
}

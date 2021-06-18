package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model

data class PurchaseOrder(
    val id: Long,
    val branchId: Long,
    val sku: String,
    val requested: Int,
    val dispatched: Int,
    val cost: Double,
    val supplier: String,
    val status: Status,
    val dispatchId: Long) {

    enum class Status {
        PENDING, ACCEPTED, CONFIRMED, REJECTED
    }
}

package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model

data class Dispatch(val id: Long, val supplierId: Long, val totalCost: Double, val status: Status) {

    enum class Status {
        ACCEPTED, REJECTED_UNKNOWN_SUPPLIER, CONFIRMED
    }
}

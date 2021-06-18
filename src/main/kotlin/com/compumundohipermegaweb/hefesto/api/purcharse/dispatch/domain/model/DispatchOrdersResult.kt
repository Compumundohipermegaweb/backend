package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model

class DispatchOrdersResult(var status: Dispatch.Status, var errors: MutableList<DispatchError>)

data class DispatchError(val code: Code, val dispatchedItem: DispatchedItem) {

    enum class Code {
        UNKNOWN_SKU,
        NO_PURCHASE_ORDER,
        PRICE_DIFFERENCE_TOO_HIGH,
        DISPATCHED_AMOUNT_TOO_HIGH,
        ALREADY_DISPATCHED
    }
}

package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

data class DispatchResponse(val status: String, val errors: List<DispatchErrorResponse>)

data class DispatchErrorResponse(val code: String, val dispatchedItemResponse: DispatchedItemResponse)

data class DispatchedItemResponse(val sku: String, val amount: Int, val unitPrice: Double, val subtotal: Double)

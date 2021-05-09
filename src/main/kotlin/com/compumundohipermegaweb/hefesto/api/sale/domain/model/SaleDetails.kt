package com.compumundohipermegaweb.hefesto.api.sale.domain.model

data class SaleDetails(
    var itemsDetails: List<ItemDetail>,
    var paymentDetails: List<PaymentDetail>)

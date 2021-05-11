package com.compumundohipermegaweb.hefesto.api.sale.domain.model

data class SaleDetails(
        var details: List<SaleDetail>,
        var payments: List<SalePayment>)

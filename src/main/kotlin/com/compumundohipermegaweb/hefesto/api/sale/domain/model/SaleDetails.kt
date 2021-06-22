package com.compumundohipermegaweb.hefesto.api.sale.domain.model

import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount

data class SaleDetails(
        var details: List<SaleDetail>,
        var payments: List<SalePayment>,
        val discount: Discount?)

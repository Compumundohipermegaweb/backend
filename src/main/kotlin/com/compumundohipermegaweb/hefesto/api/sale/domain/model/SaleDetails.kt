package com.compumundohipermegaweb.hefesto.api.sale.domain.model

import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount
import java.util.ArrayList

data class SaleDetails(
        var details: List<SaleDetail>,
        var payments: List<SalePayment>,
        val discount: Discount?) {
    constructor(details: ArrayList<SaleDetail>, payments: ArrayList<SalePayment>) : this(details, payments, null)
}

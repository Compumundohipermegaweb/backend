package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails

data class Invoice(val id: Long,
                   val saleId: Long,
                   val type: String,
                   val client: Client,
                   val branchId: Long,
                   val branchAddress: String,
                   val branchContact: String,
                   val cuit: String,
                   val activitySince: String,
                   val saleDetails: SaleDetails,
                   val subTotal: Double,
                   val ivaSubTotal: Double,
                   val total: Double)
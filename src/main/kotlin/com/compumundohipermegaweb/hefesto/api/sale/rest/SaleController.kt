package com.compumundohipermegaweb.hefesto.api.sale.rest

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientResponse
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.InvoiceResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList


@RestController
@RequestMapping("/api/sales")
class SaleController(private val invoiceSale: InvoiceSale) {

    @PostMapping
    fun invoiceSale(@RequestBody sale: SaleRequest): ResponseEntity<InvoiceResponse> {
        return ResponseEntity.ok(invoiceSale.invoke(sale).toInvoiceResponse())
    }

    private fun Invoice.toInvoiceResponse() = InvoiceResponse(id, type, client.toClientResponse(), branchAddress, branchContact, cuit, activitySince, saleDetails.toSaleDetailsResponse(), subTotal, ivaSubTotal, total)

    private fun Client.toClientResponse() = ClientResponse(documentNumber, firstName, lastName, surName, category, email, contactNumber)

    private fun SaleDetails.toSaleDetailsResponse() = SaleDetailsResponse(itemsDetails.map { SaleDetailResponse(it.id, it.quantity, it.unitPrice) }.toList())
}







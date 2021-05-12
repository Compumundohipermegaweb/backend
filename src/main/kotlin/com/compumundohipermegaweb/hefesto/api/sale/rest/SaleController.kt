package com.compumundohipermegaweb.hefesto.api.sale.rest

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientResponse
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.InvoiceResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailsResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleItemDetailResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@CrossOrigin(origins = ["https://hefesto-dev.web.app", "http://localhost:4200"])
@RestController
@RequestMapping("/api/sales")
class SaleController(private val invoiceSale: InvoiceSale) {

    @PostMapping
    fun invoiceSale(@RequestBody sale: SaleRequest): ResponseEntity<InvoiceResponse> {
        return ResponseEntity.ok(invoiceSale.invoke(sale).toInvoiceResponse())
    }

    private fun Invoice.toInvoiceResponse() = InvoiceResponse(id, SimpleDateFormat("dd/MM/yyyy").format(billingDate), type, client.toClientResponse(), branchAddress, branchContact, cuit, activitySince, saleDetails.toSaleDetailsResponse(), subTotal, ivaSubTotal, total)

    private fun Client.toClientResponse() = ClientResponse(documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)

    private fun SaleDetails.toSaleDetailsResponse() = SaleDetailsResponse(details.map { SaleItemDetailResponse(it.id, it.description, it.quantity, it.unitPrice) }.toList())
}







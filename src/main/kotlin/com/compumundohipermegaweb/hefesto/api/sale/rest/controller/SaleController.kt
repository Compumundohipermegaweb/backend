package com.compumundohipermegaweb.hefesto.api.sale.rest.controller

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientResponse
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.GetClientBySaleId
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.InvoiceResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailsResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleItemDetailResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RestController
@RequestMapping("/api/sales")
class SaleController(private val invoiceSale: InvoiceSale,
                     private val getClientBySaleId: GetClientBySaleId) {

    @PostMapping
    fun invoiceSale(@RequestBody sale: SaleRequest): ResponseEntity<InvoiceResponse> {
        return ResponseEntity.ok(invoiceSale.invoke(sale).toInvoiceResponse())
    }

    @GetMapping
    @RequestMapping("/client")
    fun getSaleClient(@RequestParam("sale_id") saleId: Long): ResponseEntity<ClientResponse> {
        val client = getClientBySaleId.invoke(saleId)
        if(client != null){
            return ResponseEntity.ok(client.toClientResponse())
        }
        return ResponseEntity.ok(null)
    }

    private fun Invoice.toInvoiceResponse() = InvoiceResponse(voucherNumber, SimpleDateFormat("dd/MM/yyyy").format(billingDate), type, client.toClientResponse(), branchAddress, branchContact, cuit, activitySince, saleDetails.toSaleDetailsResponse(), subTotal, ivaSubTotal, total)

    private fun Client.toClientResponse() = ClientResponse(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)

    private fun SaleDetails.toSaleDetailsResponse() = SaleDetailsResponse(details.map { SaleItemDetailResponse(it.id, it.description, it.quantity, it.unitPrice) }.toList())
}







package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientResponse
import com.compumundohipermegaweb.hefesto.api.invoice.domain.action.RegisterInvoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sales")
class InvoiceController(private val registerInvoice: RegisterInvoice) {

    @PostMapping
    fun postClient(@RequestBody invoiceRequest: InvoiceRequest): ResponseEntity<InvoiceResponse> {
        val invoice = invoiceRequest.toInvoice()
        return ResponseEntity.ok(registerInvoice(invoice).toInvoiceResponse())
    }

    private fun Invoice.toInvoiceResponse(): InvoiceResponse {
        val branchResponse = BranchResponse("Dirrección", "contacto", "cuit", "gross income", "activity since")

        val iva: Double = (21*total)/100
        val subTotal: Double = total - iva

        return InvoiceResponse(
            id,
            type,
            client.toClientResponse(),
            branchResponse,
            items.toShortItemsResponse(),
            subTotal,
            total,
            iva,
            shortPaymentsDetails.toPaymentsResponse())
    }

    private fun InvoiceRequest.toInvoice(): Invoice {

        return Invoice(0L,
                        type,
                        clientRequest.toClient(),
                        idSalesman,
                        idBranch,
                        itemsRequest.toShortItems(),
                        total,
                        paymentsDetailsRequest.toShortPayments())
    }

    private fun ClientRequest.toClient(): Client {
        return Client(0L, documentNumber, firstName, lastName, surName, category, email, contactNumber)
    }

    private fun Client.toClientResponse(): ClientResponse {
        return ClientResponse(documentNumber, firstName, lastName, surName, category, email, contactNumber)
    }

    private fun ShortPayments.toPaymentsResponse(): ShortPaymentsResponse {
        var shortPaymentsList: List<ShortPaymentDetailsResponse> = ArrayList()
        for(payment in payments){
            shortPaymentsList+=ShortPaymentDetailsResponse(payment.id, payment.subTotal)
        }
        return ShortPaymentsResponse(shortPaymentsList)
    }

    private fun ShortItems.toShortItemsResponse(): ShortItemsResponse {
        var shortItemsList: List<ShortItemResponse> = ArrayList()
        for(item in items){
            val subTotal = item.quantity*item.unitPrice
                shortItemsList+=ShortItemResponse(item.id, item.quantity, item.unitPrice, subTotal)
        }
        return ShortItemsResponse(shortItemsList)
    }

    private fun ShortItemsRequest.toShortItems(): ShortItems {

        var shortItemsList: List<ShortItem> = ArrayList()
        for(item in items){
            shortItemsList+=ShortItem(item.id, item.quantity, item.unitPrice)
        }
        return ShortItems(shortItemsList)
    }

    private fun ShortPaymentsRequest.toShortPayments(): ShortPayments {
        var shortPaymentsList: List<ShortPaymentsDetails> = ArrayList()
        for(payment in payments){
            shortPaymentsList+=ShortPaymentsDetails(payment.id, payment.subTotal)
        }
        return ShortPayments(shortPaymentsList)
    }
}



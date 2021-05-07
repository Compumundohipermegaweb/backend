package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientResponse
import com.compumundohipermegaweb.hefesto.api.invoice.domain.action.RegisterInvoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(private val registerInvoice: RegisterInvoice) {

    @PostMapping
    fun postClient(invoiceRequest: InvoiceRequest): ResponseEntity<InvoiceResponse> {
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

    private fun ShortItem.toItemResponse(): ItemResponse {
        val totalPrice: Double = unitPrice*quantity.toDouble()
        return ItemResponse(id, quantity, unitPrice, totalPrice)
    }

    private fun ShortPaymentsDetails.toPaymentsDetailsResponse(): PaymentsDetailsResponse {
        return PaymentsDetailsResponse(id, subTotal)
    }

    private fun ItemRequest.toShortItem(): ShortItem {
        return ShortItem(id, quantity, unitPrice)
    }

    private fun PaymentDetailsRequest.toShortPaymentsDetails(): ShortPaymentsDetails {
        return ShortPaymentsDetails(id, subTotal)
    }

    private fun ShortPayments.toPaymentsResponse(): ShortPaymentsResponse {
        return ShortPaymentsResponse(payments)
    }

    private fun ShortItems.toShortItemsResponse(): ShortItemsResponse {
        return ShortItemsResponse(items)
    }

    private fun ShortItemsRequest.toShortItems(): ShortItems {
        return ShortItems(items)
    }

    private fun ShortPaymentsRequest.toShortPayments(): ShortPayments {
        return ShortPayments(payments)
    }
}
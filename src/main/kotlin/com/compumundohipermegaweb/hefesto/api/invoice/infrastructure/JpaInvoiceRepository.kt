package com.compumundohipermegaweb.hefesto.api.invoice.infrastructure

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.ShortItems
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.ShortPayments
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.repository.CrudRepository

class JpaInvoiceRepository(private val repository: SpringDataInvoiceRepository): InvoiceRepository {
    override fun save(invoice: Invoice): Invoice{
        return repository.save(invoice.toDao()).toInvoice()
    }
}

private fun InvoiceDao.toInvoice(): Invoice {
    val client = Client(0L, "99999999", "Consumidor","Final", "", "", "", "")
    val mapper = ObjectMapper()
    val itemsList: ShortItems = mapper.readValue(items, ShortItems().javaClass)
    val paymentsList: ShortPayments = mapper.readValue(items, ShortPayments().javaClass)


    return Invoice(id, type, client, idSalesman, idBranch, itemsList, total, paymentsList)
}

fun ShortPayments(): ShortPayments {
    return ShortPayments()
}

private fun ShortItems(): ShortItems {
    return ShortItems()
}

private fun Invoice.toDao(): InvoiceDao {

    val mapper = ObjectMapper()
    val itemsStringJson: String = mapper.writeValueAsString(items)
    val paymentDetailsStringJson: String = mapper.writeValueAsString(shortPaymentsDetails)

    return InvoiceDao(id, type, client.id, idSalesman, idBranch, itemsStringJson, total, paymentDetailsStringJson)
}

interface SpringDataInvoiceRepository: CrudRepository<InvoiceDao, Long>
package com.compumundohipermegaweb.hefesto.api.invoice.infrastructure

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.repository.CrudRepository

class JpaInvoiceRepository(private val repository: SpringDataInvoiceRepository): InvoiceRepository {
    override fun save(invoice: Invoice): Invoice{
        return repository.save(invoice.toDao()).toInvoice()
    }
}

private fun Invoice.toDao(): InvoiceDao {

    val mapper = ObjectMapper()
    var initialItems: List<ShortItem> = items.items
    val itemsStringJson: String = mapper.writeValueAsString(initialItems)
    val paymentDetailsStringJson: String = mapper.writeValueAsString(shortPaymentsDetails.payments)

    return InvoiceDao(id, type, client.id, idSalesman, idBranch, itemsStringJson, total, paymentDetailsStringJson)
}

private fun InvoiceDao.toInvoice(): Invoice {
    val client = Client(0L, "99999999", "Consumidor","Final", "Final", "------", "consumidorFinal@final.com", "9999999999")
    val mapper = ObjectMapper()
    val items: ArrayList<ShortItem> = mapper.readValue(items, mapper.typeFactory.constructCollectionType(ArrayList::class.java, ShortItem::class.java))
    val payments: ArrayList<ShortPaymentsDetails> = mapper.readValue(shortPaymentsDetails, mapper.typeFactory.constructCollectionType(ArrayList::class.java, ShortPaymentsDetails::class.java))

    return Invoice(id, type, client, idSalesman, idBranch, ShortItems(items), total, ShortPayments(payments))
}

interface SpringDataInvoiceRepository: CrudRepository<InvoiceDao, Long>
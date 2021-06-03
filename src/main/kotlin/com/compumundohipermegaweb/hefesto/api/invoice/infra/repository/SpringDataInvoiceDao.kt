package com.compumundohipermegaweb.hefesto.api.invoice.infra.repository

import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao
import org.springframework.data.repository.CrudRepository

interface SpringDataInvoiceDao: CrudRepository<InvoiceDao, Long> {
}
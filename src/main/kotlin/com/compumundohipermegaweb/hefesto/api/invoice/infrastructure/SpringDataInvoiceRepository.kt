package com.compumundohipermegaweb.hefesto.api.invoice.infrastructure

import org.springframework.data.repository.CrudRepository

interface SpringDataInvoiceRepository: CrudRepository<InvoiceDao, Long>
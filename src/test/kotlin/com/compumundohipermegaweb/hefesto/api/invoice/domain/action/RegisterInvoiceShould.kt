package com.compumundohipermegaweb.hefesto.api.invoice.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.*
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RegisterInvoiceShould {
    private lateinit var registerInvoice: RegisterInvoice
    private lateinit var invoiceRepository: InvoiceRepository
    private lateinit var registeredInvoice: Invoice

    @Test
    fun `register a invoice`() {
        givenInvoiceRepository()
        givenRegisterInvoice()

        whenRegisteringInvoice(INVOICE)

        thenTheInvoiceIsSuccessfullyRegister()
    }

    private fun givenInvoiceRepository() {
        invoiceRepository = mock(InvoiceRepository::class.java)
        `when`(invoiceRepository.save(INVOICE)).thenReturn(INVOICE)

    }

    private fun givenRegisterInvoice() {
        registerInvoice = RegisterInvoice(invoiceRepository)
    }

    private fun whenRegisteringInvoice(invoice: Invoice) {
        registeredInvoice = registerInvoice(invoice)
    }

    private fun thenTheInvoiceIsSuccessfullyRegister() {
        then(registeredInvoice).isNotNull
    }
    private companion object {
        val client = Client(0L, "", "", "", "", "", "", "")
        private val INVOICE = Invoice(0L, "", client, 0L, 0L, ShortItems(ArrayList()), 00.00, ShortPayments(ArrayList()))
    }
}
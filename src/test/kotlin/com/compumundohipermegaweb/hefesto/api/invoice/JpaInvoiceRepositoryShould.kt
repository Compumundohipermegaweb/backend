package com.compumundohipermegaweb.hefesto.api.invoice

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.JpaInvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.repository.SpringDataInvoiceDao
import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaInvoiceRepositoryShould {
    private lateinit var springDataInvoiceDao: SpringDataInvoiceDao
    private lateinit var invoiceRepository: InvoiceRepository

    private lateinit var savedInvoice: Invoice

    @Test
    fun `save the invoice`() {
        givenSpringDataInvoiceClient()
        givenInvoiceRepository()

        whenSavingTheInvoice()

        thenTheInvoiceIsSaved()
    }

    private fun givenSpringDataInvoiceClient() {
        springDataInvoiceDao = mock()
        `when`(springDataInvoiceDao.save(INVOICE_DAO)).thenReturn(INVOICE_DAO)
        `when`(springDataInvoiceDao.save(SECOND_SAVED_INVOICE_DAO)).thenReturn(SECOND_SAVED_INVOICE_DAO)
    }

    private fun givenInvoiceRepository() {
        invoiceRepository = JpaInvoiceRepository(springDataInvoiceDao)
    }

    private fun whenSavingTheInvoice() {
        savedInvoice = invoiceRepository.save(INVOICE)
    }

    private fun thenTheInvoiceIsSaved() {
        verify(springDataInvoiceDao).save(INVOICE_DAO)
        verify(springDataInvoiceDao).save(SECOND_SAVED_INVOICE_DAO)
        then(savedInvoice).isEqualTo(SECOND_SAVED_INVOICE)
    }

    private companion object {
        val DATE = Date()
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val SALE_ITEM_DETAIL = listOf(SaleDetail(0L, "","",1, 200.50))
        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L, "EFECTIVO",200.50))
        val INVOICE = Invoice(0L, 0L, "", DATE, "", CLIENT, 0L, "", "", "", "", SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL), 0.0, 0.0, 0.0)
        val INVOICE_DAO =  InvoiceDao(0L, 0L, "", DATE, "FV", "", 0L, 0.0, 0.0, 0.0)
        val SECOND_SAVED_INVOICE_DAO = InvoiceDao(0L, 0L, "", DATE, "FV", "", 0L, 0.0, 0.0, 0.0)
        val SECOND_SAVED_INVOICE = Invoice(0L, 0L, "FV0000000000000", DATE, "", CLIENT, 0L, "", "", "", "", SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL), 0.0, 0.0, 0.0)

    }
}
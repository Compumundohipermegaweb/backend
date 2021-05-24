package com.compumundohipermegaweb.hefesto.api.invoice.infra.repository

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao

class JpaInvoiceRepository(private val springDataInvoiceClient: SpringDataInvoiceClient): InvoiceRepository  {
    override fun save(invoice: Invoice): Invoice {
        val invoiceSaved = springDataInvoiceClient.save(invoice.toInvoiceDao())
        return springDataInvoiceClient.save(buildVoucherNumber(invoiceSaved)).toInvoice(invoice, invoiceSaved.voucherNumber)
    }

    private fun buildVoucherNumber(invoiceDao: InvoiceDao): InvoiceDao {
        val voucherNumber = invoiceDao.id.toString()
        val quantityOfZeros = StringBuilder()
        for(i in voucherNumber.length..7){ quantityOfZeros.append("0") }
        invoiceDao.voucherNumber = quantityOfZeros.toString()+voucherNumber
        return invoiceDao
    }

    private fun buildBranchNumber(branchId: Long): String {
        val branchNumber = branchId.toString()
        val quantityOfZeros = StringBuilder()
        for(i in branchNumber.length..4){ quantityOfZeros.append("0") }
        return quantityOfZeros.toString()+branchNumber
    }

    private fun InvoiceDao.toInvoice(invoice: Invoice, voucherNumber: String): Invoice {
        return Invoice(id, saleId, documentType+type+buildBranchNumber(branchId)+voucherNumber, billingDate, type, invoice.client, branchId, invoice.branchAddress, invoice.branchContact, invoice.cuit, invoice.activitySince, invoice.saleDetails, subTotal, ivaSubTotal, total)
    }

    private fun Invoice.toInvoiceDao(): InvoiceDao {
        return InvoiceDao(id, saleId, voucherNumber, billingDate, "FV", type, branchId, subTotal, ivaSubTotal, total)
    }
}
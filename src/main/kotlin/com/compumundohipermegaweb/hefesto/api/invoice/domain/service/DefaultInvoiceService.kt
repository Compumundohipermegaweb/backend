package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import java.util.*

class DefaultInvoiceService(private val invoiceRepository: InvoiceRepository,
                            private val cashStartEndRepository: CashStartEndRepository,
                            private val cashMovementRepository: CashMovementRepository): InvoiceService {

    override fun invoiceSale(sale: Sale, saleRequest: SaleRequest) : Invoice {
        val iva = calculateIva(sale)
        val subTotal = calculateSubTotal(sale, iva)
        val invoice = createInvoice(sale, subTotal, iva)

        registerCashMovement(saleRequest, sale)

        return invoiceRepository.save(invoice)
    }

    override fun updateInvoice(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    private fun calculateIva(savedSale: Sale): Double {
        return when(savedSale.type){
            "A" -> (21 * savedSale.total) / 100
            else -> 0.0
        }
    }

    private fun calculateSubTotal(savedSale: Sale, iva: Double): Double {
        return when(savedSale.type){
            "A" -> savedSale.total - iva
            else -> savedSale.total
        }
    }

    private fun registerCashMovement(saleRequest: SaleRequest, sale: Sale) {
        val cashStartEnd = cashStartEndRepository.findByCashIdAndEndDate(saleRequest.branchId)

        if(saleRequest.category.equals("LOCAL")) {
            cashMovementRepository.save(CashMovement(0L, cashStartEnd.id, "INGRESO", Date(), 2L, "VENTA", 0L, sale.id, saleRequest.salesmanId, sale.total, "VENTA LOCAL"), cashStartEnd.id)
        } else {
            cashMovementRepository.save(CashMovement(0L, cashStartEnd.id, "INGRESO", Date(), 1L, "VENTA", 0L, sale.id, saleRequest.salesmanId, sale.total, "VENTA ONLINE"), cashStartEnd.id)
        }
    }

    private fun createInvoice(sale: Sale, subTotal: Double, iva: Double) =
            Invoice(id =0L,
                    voucherNumber = "",
                    billingDate = Date(),
                    type = sale.type,
                    client = sale.client,
                    branchId = sale.branchId,
                    branchAddress = "Domicilio fiscal",
                    branchContact = "1134567892",
                    cuit = "27-28033514-8",
                    activitySince = "01/01/2021",
                    saleDetails = sale.saleDetails,
                    subTotal = subTotal,
                    ivaSubTotal = iva,
                    total = sale.total,
                    saleId = sale.id)
}


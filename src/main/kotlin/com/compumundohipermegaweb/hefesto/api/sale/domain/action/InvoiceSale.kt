package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest

class InvoiceSale(private val saleService: SaleService,
                  private val invoiceService: InvoiceService
) {
    operator fun invoke(saleRequest: SaleRequest): Invoice {

        val savedSale = saleService.save(saleRequest.toSale())

        val iva = (21*savedSale.total)/100
        val subTotal = savedSale.total - iva

        return invoiceService.save(Invoice(0L,
                                            savedSale.id,
                                            savedSale.type,
                                            savedSale.branchId,
                                            subTotal,
                                            iva,
                                            savedSale.total))
    }

    private fun SaleRequest.toSale(): Sale {
        return Sale(0L,
                    type,
                    0L,
                    idSalesman,
                    idBranch,
                    saleDetailsRequest.toSaleDetails(),
                    total)
    }

    private fun SaleDetailsRequest.toSaleDetails(): SaleDetails {
        lateinit var saleDetails: List<SaleDetail>
        for(item in saleDetailsRequest){
            saleDetails+=SaleDetail(item.id, item.quantity, item.unitPrice)
        }
        return SaleDetails(saleDetails)
    }
}



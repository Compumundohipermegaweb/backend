package com.compumundohipermegaweb.hefesto.api.online.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.RejectedSaleService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class ProcessOnlineSale(private val invoiceSale: InvoiceSale,
                        private val stockService: StockService,
                        private val itemService: ItemService,
                        private val clientService: ClientService,
                        private val rejectedSaleService: RejectedSaleService)
{
    private lateinit var acceptedItems: List<SaleDetailRequest>
    private lateinit var rejectedItems: List<RejectedItemDetail>

    private val rejectPriceMotive = "The difference in price is greater than 5%"
    private val rejectStockMotive = "No stock available"
    private val rejectPartialStockMotive = "There is not enough stock to cover the total quantity requested"
    private val rejectedItemMotive = "The item does not exist in our item master"

    operator fun invoke(onlineSaleRequest: SaleRequest): Invoice? {

        var invoice: Invoice? = null
        var idSale: Long? = null
        val rejectionLevel: String

        acceptedItems = onlineSaleRequest.saleDetailsRequest.detailsRequest
        rejectedItems = ArrayList()

        var client: Client? = clientService.findByDocument(onlineSaleRequest.clientRequest.documentNumber)
        if(client == null) {
            client = clientService.save(onlineSaleRequest.clientRequest.toClient())
        } else {
            if(client.address.isNullOrEmpty()) {
                client.address = onlineSaleRequest.clientRequest.address
            }
        }

        if(client.isValid()) {
            validatePriceOfSaleItemsRequest(onlineSaleRequest)

            if(acceptedItems.isNotEmpty()){
                onlineSaleRequest.saleDetailsRequest.detailsRequest = acceptedItems
                invoice = invoiceSale.invoke(onlineSaleRequest)
                idSale = invoice.saleId
            }
            if(rejectedItems.isNotEmpty()){
                rejectionLevel = if(acceptedItems.isEmpty()) {
                    "TOTAL"
                } else {
                    "PARCIAL"
                }
                rejectedSaleService.saveRejectedSale(createRejectedSale(idSale, "invalid items or stocks", rejectionLevel), rejectedItems)
            }
        } else {
            rejectionLevel = "TOTAL"
            rejectedItems = onlineSaleRequest.saleDetailsRequest.detailsRequest.map { it.toRejectedItemDetail("The client did not provide an address") }.toList()
            rejectedSaleService.saveRejectedSale(createRejectedSale(idSale, "The client did not provide an address",rejectionLevel), rejectedItems)
        }
        return invoice
    }

    private fun Client.isValid(): Boolean {
        if(address == null){
            return false
        }
        if(address!!.isBlank() || address!!.isEmpty()){
            return false
        }
        return true
    }

    private fun ClientRequest.toClient(): Client {
        return Client(0L, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
    }

    private fun Item.priceIsValid(detailRequest: SaleDetailRequest): Boolean {
        val saleRequestPrice = detailRequest.unitPrice
        if(saleRequestPrice < price) {
            val difference = price - saleRequestPrice
            val percentage = (difference * 100)/price
            if(percentage > 5){
                return false
            }
        }
        return true
    }

    private fun validatePriceOfSaleItemsRequest(saleRequest: SaleRequest) {
        saleRequest.saleDetailsRequest.detailsRequest.forEach {
            val item = itemService.findItemById(it.id)
            if(item != null){
                if(!item.priceIsValid(it)){
                    rejectedItems = rejectedItems+RejectedItemDetail(0L, item.id ,item.sku, it.description, it.quantity, it.unitPrice, rejectPriceMotive)
                    acceptedItems = acceptedItems-it
                } else{
                    validateStock(it, item)
                }
            } else {
                rejectedItems = rejectedItems+RejectedItemDetail(0L, it.id, "", it.description, it.quantity, it.unitPrice, rejectedItemMotive)
                acceptedItems = acceptedItems-it
            }
        }
    }

    private fun validateStock(itemDetail: SaleDetailRequest, item: Item) {
        val stock = stockService.findBySku(item.sku)
        if(stock != null) {
            if(stock.stockTotal != 0) {
                if(stock.stockTotal < itemDetail.quantity) {
                    val requestedAmount = itemDetail.quantity
                    val quantityAvailable = stock.stockTotal
                    itemDetail.quantity = quantityAvailable
                    rejectedItems = rejectedItems+RejectedItemDetail(0L, item.id, item.sku, itemDetail.description, requestedAmount-quantityAvailable, itemDetail.unitPrice, rejectPartialStockMotive)
                }
            } else {
                rejectedItems = rejectedItems+RejectedItemDetail(0L, item.id, item.sku, itemDetail.description, itemDetail.quantity, itemDetail.unitPrice, rejectStockMotive)
                acceptedItems = acceptedItems-itemDetail
            }
        } else {
            rejectedItems = rejectedItems+RejectedItemDetail(0L, item.id, item.sku, itemDetail.description, itemDetail.quantity, itemDetail.unitPrice, rejectStockMotive)
            acceptedItems = acceptedItems-itemDetail
        }
    }

    private fun createRejectedSale(saleId: Long?, motive: String, level: String): RejectedSale {
        return RejectedSale(0L, saleId, 0.0, "VENTA_ONLINE", motive, level)
    }

    private fun SaleDetailRequest.toRejectedItemDetail(motive: String): RejectedItemDetail {
        return RejectedItemDetail(0L, id, "", description, quantity, unitPrice, motive)
    }
}



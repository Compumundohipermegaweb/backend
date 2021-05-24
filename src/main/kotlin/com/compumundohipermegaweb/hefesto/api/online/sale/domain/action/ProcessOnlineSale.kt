package com.compumundohipermegaweb.hefesto.api.online.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class ProcessOnlineSale(private val invoiceSale: InvoiceSale,
                        private val stockService: StockService,
                        private val itemService: ItemService,
                        private val clientService: ClientService)
{
    private lateinit var rejectedSaleDetailRequest: List<SaleDetailRequest>
    private lateinit var rejectMotives: List<String>

    operator fun invoke(onlineSaleRequest: SaleRequest) {
        if(onlineSaleRequest.clientRequest.isValid()) {
            val client = clientService.findByDocument(onlineSaleRequest.clientRequest.documentNumber)
            if(client == null) {
                clientService.save(onlineSaleRequest.clientRequest.toClient())
            }
            validatePriceOfSaleItemsRequest(onlineSaleRequest)

            for(rejectedItem in rejectedSaleDetailRequest) {
                for(requestItem in onlineSaleRequest.saleDetailsRequest.detailsRequest){
                    if(requestItem == rejectedItem) {
                        onlineSaleRequest.saleDetailsRequest.detailsRequest-requestItem
                    }
                }
            }
            if(onlineSaleRequest.saleDetailsRequest.detailsRequest.isNotEmpty()){
                invoiceSale.invoke(onlineSaleRequest)
            } else {
                //proccessRejectedSale(onlineSaleRequest, rejectedSaleDetailRequest, "")
            }

            if(rejectedSaleDetailRequest.isNotEmpty()){

            }
        } else {
            //registrar pedido rechazado
        }

    }

    private fun ClientRequest.isValid(): Boolean {
        if(address == null) {
            return false
        }
        if(address.isBlank() || address.isEmpty()){
            return false
        }
        return true
    }

    private fun ClientRequest.toClient(): Client {
        return Client(0L, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
    }

    private fun validatePriceOfSaleItemsRequest(saleRequest: SaleRequest) {
        val rejectPriceMotive = "the difference in price is greater than 5%"
        val rejectStockMotive = "no stock available"
        saleRequest.saleDetailsRequest.detailsRequest.forEach {
            val item = itemService.findItemById(it.id)
            if(item != null){
                if(!item.priceIsValid(it)){
                    rejectedSaleDetailRequest+it
                    if(!rejectPriceMotive.contains(rejectPriceMotive)){
                        rejectMotives+rejectPriceMotive
                    }
                }
                if(!item.thereIsStock(it.quantity)){
                    if(!rejectedSaleDetailRequest.contains(it)){
                        rejectedSaleDetailRequest+it
                        if(!rejectPriceMotive.contains(rejectStockMotive)){
                            rejectMotives+rejectStockMotive
                        }
                    }
                }
            } else {
                rejectedSaleDetailRequest+it
            }
        }
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

    private fun Item.thereIsStock(quantity: Int): Boolean {
        val stock = stockService.findBySku(sku)
        return if (stock != null) {
            stock.stockTotal >= quantity
        } else {
            false
        }
    }
}









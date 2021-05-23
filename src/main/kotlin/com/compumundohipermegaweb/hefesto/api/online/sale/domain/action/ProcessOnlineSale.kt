package com.compumundohipermegaweb.hefesto.api.online.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class ProcessOnlineSale(private val stockService: StockService,
                        private val clientService: ClientService) {
    operator fun invoke(onlineSaleRequest: SaleRequest) {
        if(onlineSaleRequest.clientRequest.isValid()) {
            val client = clientService.findByDocument(onlineSaleRequest.clientRequest.documentNumber)
        } else {
            //registrar pedido rechazado
        }

    }

    private fun ClientRequest.isValid(): Boolean {
        if(address.isEmpty() || address.isBlank() || address == null){
            return false
        }
        return true
    }
}



package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetClientBySaleId(private val saleRepository: SaleRepository,
                        private val clientRepository: ClientRepository) {

    operator fun invoke(saleId: Long): Client? {
        val client: Client?
        val sale = saleRepository.findById(saleId)
        if(sale != null) {
            client = clientRepository.findById(sale.clientId)
            if(client != null) {
                return client
            }
        }
        return null
    }
}
package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService

class GetPaymentMethodsByClient(private val paymentMethodService: PaymentMethodService,
                                private val clientService: ClientService,
                                private val checkingAccountService: CheckingAccountService) {
    operator fun invoke (clientId: Long): List<PaymentMethod> {
        var paymentMethods = paymentMethodService.findAllPaymentMethod()
        paymentMethods = filterPaymentMethodsByClient(clientId, paymentMethods)
        return paymentMethods.filter { it.state == "ACTIVE" }
    }

    private fun filterPaymentMethodsByClient(clientId: Long, paymentMethods: List<PaymentMethod>): List<PaymentMethod> {
        val client = clientService.findById(clientId)
        if(client != null) {
            if(client.state == "MOROSO"){
                paymentMethods.filter { it.paymentMethod != "CUENTA CORRIENTE" }
            } else {
                val checkingAccount = checkingAccountService.findCheckingAccountByClientId(clientId)
                if(checkingAccount == null) {
                    paymentMethods.filter { it.paymentMethod != "CUENTA CORRIENTE" }
                }
            }
        }
        return paymentMethods
    }
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest

class UpdatePaymentDetails(private val cashMovementRepository: CashMovementRepository,
                           private val salePaymentRepository: SalePaymentRepository,
                           private val saleRepository: SaleRepository,
                           private val checkingAccountRepository: CheckingAccountRepository
) {

    operator fun invoke(movementId: Long, paymentRequest: List<PaymentRequest>) {
        val movement = cashMovementRepository.findById(movementId)
        if(movement != null) {
            if(movement.sourceDescription == "VENTA") {
                val payments = salePaymentRepository.findBySaleId(movement.transactionId)
                payments.forEach { salePaymentRepository.delete(it, movement.transactionId) }
                paymentRequest.forEach { salePaymentRepository.save(it.toSalePayment(movement.transactionId), movement.transactionId) }

                payments.forEach {
                    if(it.paymentMethodId == 5L) {//"CUENTA_CORRIENTE"
                        val sale = saleRepository.findById(movement.transactionId)
                        if(sale != null) {
                            val clientId = sale.clientId
                            val checkingAccount = checkingAccountRepository.findCheckingAccountByClientId(clientId)
                            if(checkingAccount != null){
                                checkingAccountRepository.updateBalanceDue(clientId, checkingAccount.balanceDue + it.subTotal)
                                checkingAccountRepository.updateBalance(clientId, checkingAccount.creditLimit - it.subTotal)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun PaymentRequest.toSalePayment(saleId: Long): SalePayment {
        return SalePayment(0L,saleId,method.id,cardId,lastDigits,email,sub_total )
    }
}



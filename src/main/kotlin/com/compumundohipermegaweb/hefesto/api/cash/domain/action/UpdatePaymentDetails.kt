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

    operator fun invoke(movementId: Long, paymentRequest: List<PaymentRequest>): Boolean {
        val movement = cashMovementRepository.findById(movementId)
        var balanceDue  = 0.0
        var balance = 0.0
        var flagDeleteCC = false
        if(movement != null) {
            if(movement.sourceDescription == "VENTA") {
                val payments = salePaymentRepository.findBySaleId(movement.transactionId)
                val sale = saleRepository.findById(movement.transactionId)
                if(sale != null) {
                    val clientId = sale.clientId
                    payments.forEach {
                        if(it.paymentMethodId == 5L) {
                            val checkingAccount = checkingAccountRepository.findCheckingAccountByClientId(clientId)
                            if (checkingAccount != null) {
                                 balanceDue = roundDecimal(checkingAccount.balanceDue - it.subTotal)
                                 balance = roundDecimal(checkingAccount.balance + it.subTotal)
                                if (balanceDue >= 0 && balance <= checkingAccount.creditLimit) {
                                    checkingAccountRepository.updateBalanceDue(clientId, balanceDue)
                                    checkingAccountRepository.updateBalance(clientId, balance)
                                    flagDeleteCC= true
                                } else {
                                    return false
                                }
                            } else {
                                return false
                            }
                        }
                        salePaymentRepository.delete(it, movement.transactionId)
                    }

                    paymentRequest.forEach {
                        if(it.method.id == 5L) {
                            val checkingAccount = checkingAccountRepository.findCheckingAccountByClientId(clientId)
                            if(checkingAccount != null){
                                if (!flagDeleteCC){
                                    balanceDue = checkingAccount.balanceDue
                                    balance = checkingAccount.balance
                                }
                                val balanceDueNew = roundDecimal(balanceDue + it.sub_total)
                                val balanceNew = roundDecimal(balance - it.sub_total)
                                if (balanceDueNew <= checkingAccount.creditLimit && balanceNew >= 0){
                                    checkingAccountRepository.updateBalanceDue(clientId, balanceDueNew)
                                    checkingAccountRepository.updateBalance(clientId, balanceNew)
                                } else {
                                    return false
                                }
                            } else {
                                return false
                            }
                        }
                        salePaymentRepository.save(it.toSalePayment(movement.transactionId), movement.transactionId)
                    }
                    return true
                } else {
                    return false
                }
            }
        }
        return false
    }

    private fun PaymentRequest.toSalePayment(saleId: Long): SalePayment {
        return SalePayment(0L,saleId,method.id,cardId,lastDigits,email,sub_total )
    }

    private fun roundDecimal(total : Double): Double {
       return Math.round(total * 100) / 100.0
    }
}



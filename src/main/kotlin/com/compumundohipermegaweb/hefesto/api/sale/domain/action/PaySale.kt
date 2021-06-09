package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaySaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest

class PaySale(private val cashMovementRepository: CashMovementRepository,
              private val salePaymentRepository: SalePaymentRepository,
              private val saleRepository: SaleRepository,
              private val checkingAccountRepository: CheckingAccountRepository) {

    operator fun invoke(paySaleRequest: PaySaleRequest) {
        val paymentDetails = paySaleRequest.paymentDetails
        val movement = paySaleRequest.cashMovement
        val savedPayment = paymentDetails.map { salePaymentRepository.save(it.toSalePayment(movement.transactionId), movement.transactionId) }
        //movement.paymentMethodId = savedPayment[0].id
        cashMovementRepository.save(movement, movement.cashStartEndId)

        paymentDetails.forEach {
            if(it.method.type == "CUENTA_CORRIENTE") {
                val sale = saleRepository.findById(movement.transactionId)
                if(sale != null) {
                    val clientId = sale.clientId
                    val checkingAccount = checkingAccountRepository.findCheckingAccountByClientId(clientId)
                    if(checkingAccount != null){
                        checkingAccountRepository.updateBalanceDue(clientId, checkingAccount.balanceDue + it.sub_total)
                        checkingAccountRepository.updateBalance(clientId, checkingAccount.creditLimit - it.sub_total)
                    }
                }
            }
        }
    }

    private fun PaymentRequest.toSalePayment(saleId: Long): SalePayment {
        return SalePayment(0L,saleId,method.id,cardId,lastDigits,email,sub_total)
    }
}





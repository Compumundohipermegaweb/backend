package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Income
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetAllIncomes(private val cashMovementRepository: CashMovementRepository,
                    private val saleRepository: SaleRepository,
                    private val salePaymentRepository: SalePaymentRepository,
                    private val clientRepository: ClientRepository) {
    operator fun invoke(cashStartEndId: Long): List<Income> {
        val transactions = mutableListOf<Income>()
        val movements = cashMovementRepository.findByCashStartEndId(cashStartEndId).filter { it.movementType == "INGRESO" }

        movements.forEach {

            if(it.sourceDescription.contains("VENTA")) {
                val income = saleRepository.findById(it.sourceId)
                if(income != null) {
                    val client = clientRepository.findById(income.clientId)
                    val paymentType = mutableListOf<String>()
                    val payments = salePaymentRepository.findBySaleId(income.id)
                    for(payment in payments) {
                        paymentType += payment.type
                    }
                    transactions+=Income(it.id, it.dateTime, it.sourceId, it.sourceDescription, "", payments, it.amount, it.userId, client,it.transactionId)
                }
            } else {
                transactions+=Income(it.id, it.dateTime, it.sourceId, it.sourceDescription, "", emptyList(), it.amount, it.userId, null,it.transactionId)
            }
        }
        return transactions
    }
}
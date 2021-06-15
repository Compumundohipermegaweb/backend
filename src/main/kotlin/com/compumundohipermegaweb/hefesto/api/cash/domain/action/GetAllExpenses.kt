package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Expense
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class GetAllExpenses(private val cashMovementRepository: CashMovementRepository,
                     private val paymentMethodRepository: PaymentMethodRepository) {
    operator fun invoke(cashStartEndId: Long): List<Expense> {
        val transactions = mutableListOf<Expense>()
        val movements = cashMovementRepository.findByCashStartEndId(cashStartEndId).filter { it.movementType == "EGRESO" }

        movements.forEach {
            if(it.movementType == "EGRESO") {
                var payment = ""
                val paymentMethod = paymentMethodRepository.findById(it.paymentMethodId)
                if(paymentMethod != null) {
                    payment = paymentMethod.type
                }
                transactions+= Expense(it.id, it.dateTime, it.sourceDescription, it.detail, payment, it.amount)
            }
        }

        return transactions
    }
}
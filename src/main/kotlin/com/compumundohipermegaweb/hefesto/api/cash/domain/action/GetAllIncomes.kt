package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Income
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetAllIncomes(private val cashMovementRepository: CashMovementRepository,
                    private val saleRepository: SaleRepository,
                    private val salePaymentRepository: SalePaymentRepository) {
    operator fun invoke(cashStartEndId: Long): List<Income> {
        val transactions = mutableListOf<Income>()
        val movements = cashMovementRepository.findByCashStartEndId(cashStartEndId).filter { it.movementType == "INGRESO" }

        movements.forEach {
            if(it.transactionDescription.contains("VENTA")) {
                val income = saleRepository.findById(it.transactionId)
                if(income != null) {
                    val payment = salePaymentRepository.findBySaleId(income.id)
                    transactions+=Income(it.id, it.dateTime, it.transactionDescription, "", payment[0].type, it.amount)
                }
            }
        }

        return transactions
    }
}
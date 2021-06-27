package com.compumundohipermegaweb.hefesto.api.cash.domain.service

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import java.time.Instant
import java.util.*

class CashService(private val cashRepository: CashRepository,
                  private val cashStartEndRepository: CashStartEndRepository,
                  private val cashMovementRepository: CashMovementRepository) {

    fun registerSupplierExpense(branchId: Long, amount: Double, dispatchId: Long) {
        val cash = cashRepository.findByBranchId(branchId)!!
        val cashStartEnd = cashStartEndRepository.findByCashIdAndEndDate(cash.id)
        val cashMovement = CashMovement(
                id = 0L,
                cashStartEndId = cashStartEnd.id,
                movementType = "EGRESO",
                dateTime = Date.from(Instant.now()),
                sourceId = 10,
                sourceDescription = "PAGO_PROVEEDOR",
                paymentMethodId = 5,
                transactionId = dispatchId,
                userId = 1,
                amount = amount,
                detail = "Pago a proveedor")
        cashMovementRepository.save(cashMovement, cashStartEnd.id)
    }

}

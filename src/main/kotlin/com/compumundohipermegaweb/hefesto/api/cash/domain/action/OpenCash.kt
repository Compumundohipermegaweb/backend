package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import java.util.*

class OpenCash(private val cashRepository: CashRepository,
               private val cashStartEndRepository: CashStartEndRepository,
               private val cashMovementRepository: CashMovementRepository) {
    operator fun invoke(openRequest: OpenRequest): Cash? {
        return openCash(openRequest)
    }

    private fun openCash(openRequest: OpenRequest): Cash? {
        val cash = cashRepository.findById(openRequest.cashId)
        if (cash != null) {
            if(cash.status == "CLOSE") {
                cash.status = "OPEN"
                cashRepository.save(cash)
                val cashStartEnd = cashStartEndRepository.save(CashStartEnd(0L, cash.id, Date(), openRequest.openingBalance, openRequest.userId,null, 0.0, 0.0, Date()))
                cashMovementRepository.save(CashMovement(0L, cashStartEnd.id, "INGRESO", cashStartEnd.openDate, 0L, "APERTURA", 0L, 0L, openRequest.userId, openRequest.openingBalance, "APERTURA DE CAJA"), cashStartEnd.id)
            }
        }
        return cash
    }
}
package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CloseRequest
import java.util.*

class CloseCash(private val cashRepository: CashRepository,
                private val cashStartEndRepository: CashStartEndRepository) {

    operator fun invoke(closeRequest: CloseRequest): Cash? {
        return closeCash(closeRequest)
    }

    private fun closeCash(closeRequest: CloseRequest): Cash? {
        val cash = cashRepository.findById(closeRequest.cashId)
        if (cash != null) {
            if(cash.status == "OPEN") {
                cash.status = "CLOSE"
                cashRepository.save(cash)
                val cashEnd = cashStartEndRepository.findByCashIdAndEndDate(cash.id)
                cashEnd.closeDate = Date()
                cashEnd.realBalance = closeRequest.realBalance
                cashEnd.theoreticalBalance = closeRequest.theoreticalBalance
                cashStartEndRepository.save(cashEnd)
            }
        }
        return cash
    }
}
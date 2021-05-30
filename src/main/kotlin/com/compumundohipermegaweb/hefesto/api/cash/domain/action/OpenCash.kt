package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import java.util.*

class OpenCash(private val cashRepository: CashRepository,
               private val cashStartEndRepository: CashStartEndRepository) {
    operator fun invoke(openRequest: OpenRequest): Cash? {
        return openCash(openRequest)
    }

    private fun openCash(openRequest: OpenRequest): Cash? {
        val cash = cashRepository.findById(openRequest.cashId)
        if (cash != null) {
            if(cash.status == "CLOSE") {
                cash.status = "OPEN"
                cashRepository.save(cash)
                cashStartEndRepository.save(CashStartEnd(0L, cash.id, Date(), openRequest.openingBalance, openRequest.userId,null, openRequest.openingBalance, openRequest.openingBalance, Date()))
            }
        }
        return cash
    }
}
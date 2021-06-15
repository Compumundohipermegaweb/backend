package com.compumundohipermegaweb.hefesto.api.cash.domain.service

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.time.Instant
import java.util.*

class CashServiceShould {

    private lateinit var cashRepository: CashRepository
    private lateinit var cashStartEndRepository: CashStartEndRepository
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var cashService: CashService

    @Test
    fun `register an expense`() {
        givenCashRepository()
        givenCashStartEndRepository()
        givenCashMovementRepository()
        givenCashService()

        whenRegisteringASupplierExpense()

        thenCashMovementIsSaved()
    }

    private fun givenCashRepository() {
        cashRepository = mock()
        `when`(cashRepository.findByBranchId(11L)).thenReturn(CASH)
    }

    private fun givenCashStartEndRepository() {
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.findByCashIdAndEndDate(CASH.id)).thenReturn(CASH_START_END)
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
    }

    private fun givenCashService() {
        cashService = CashService(cashRepository, cashStartEndRepository, cashMovementRepository)
    }

    private fun whenRegisteringASupplierExpense() {
        cashService.registerSupplierExpense(11L, 350.0, 1L)
    }

    private fun thenCashMovementIsSaved() {
        verify(cashMovementRepository).save(any(), any())
    }

    private companion object {
        val CASH = Cash(1L, 11L, 1L, "")
        val CASH_START_END = CashStartEnd(1L, 1L, Date(),0.0,1L,null,0.0,0.0, Date())
    }
}
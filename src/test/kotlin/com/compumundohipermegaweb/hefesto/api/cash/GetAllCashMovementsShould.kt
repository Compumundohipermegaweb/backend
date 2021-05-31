package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetAllCashMovements
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class GetAllCashMovementsShould {
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var getAllCashMovements: GetAllCashMovements

    private lateinit var cashMovements: List<CashMovement>

    @Test
    fun `get all cash movement by cash start end id`() {
        givenCashMovementRepository()
        givenGetAllCashMovements()

        whenFindingCashMovementByCashStartEndId()

        thenTheCashMovementByCashStartEndIdAreSuccessfullyFound()
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.findByCashStartEndId(0L)).thenReturn(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT))
    }

    private fun givenGetAllCashMovements() {
        getAllCashMovements = GetAllCashMovements(cashMovementRepository)
    }

    private fun whenFindingCashMovementByCashStartEndId() {
        cashMovements = getAllCashMovements.invoke(0L)
    }

    private fun thenTheCashMovementByCashStartEndIdAreSuccessfullyFound() {
        verify(cashMovementRepository).findByCashStartEndId(0L)
        then(cashMovements).isEqualTo(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT))
    }

    private companion object {
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "", Date(), 0L, 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "", Date(), 0L, 0L, 0L,0L, 0.0, "")
        private val AND_ANOTHER_CASH_MOVEMENT = CashMovement(2L, 0L, "", Date(), 0L, 0L, 0L,0L, 0.0, "")
    }
}
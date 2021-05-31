package com.compumundohipermegaweb.hefesto.api.cash

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.JpaCashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.SpringCashMovementDao
import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.CashMovementRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class JpaCashMovementRepositoryShould {

    private lateinit var springCashMovementDao: SpringCashMovementDao
    private lateinit var cashMovementRepository: CashMovementRepository

    private lateinit var savedCashMovement: CashMovement
    private lateinit var cashMovements: List<CashMovement>

    @Test
    fun `save a cash movement`() {
        givenSpringCashMovementDao()
        givenCashMovementRepository()

        whenSavingCashMovement()

        thenTheCashMovementIsSuccessfullySaved()
    }

    @Test
    fun `get all cash movement by cash start end id`() {
        givenSpringCashMovementDao()
        givenCashMovementRepository()

        whenFindingCashMovementByCashStartEndId()

        thenTheCashMovementByCashStartEndIdAreSuccessfullyFound()
    }

    private fun givenSpringCashMovementDao() {
        springCashMovementDao = mock()
        `when`(springCashMovementDao.save(CASH_MOVEMENT.toRepresentation())).thenReturn(CASH_MOVEMENT.toRepresentation())
        `when`(springCashMovementDao.findBycashStartEndId(0L)).thenReturn(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT).map { it.toRepresentation() })
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = JpaCashMovementRepository(springCashMovementDao)
    }

    private fun whenSavingCashMovement() {
        savedCashMovement = cashMovementRepository.save(CASH_MOVEMENT, 0L)
    }

    private fun whenFindingCashMovementByCashStartEndId() {
        cashMovements = cashMovementRepository.findByCashStartEndId(0L)
    }

    private fun thenTheCashMovementIsSuccessfullySaved() {
        verify(springCashMovementDao).save(CASH_MOVEMENT.toRepresentation())
        then(savedCashMovement).isNotNull
    }

    private fun thenTheCashMovementByCashStartEndIdAreSuccessfullyFound() {
        verify(springCashMovementDao).findBycashStartEndId(0L)
        then(cashMovements).isEqualTo(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT))
    }

    private companion object {
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
        private val AND_ANOTHER_CASH_MOVEMENT = CashMovement(2L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
    }

    private fun CashMovement.toRepresentation(): CashMovementRepresentation {
        return CashMovementRepresentation(id, cashStartEndId, movementType, dateTime, transactionId, transactionDescription, paymentMethodId, cardId, userId, amount, detail)
    }
}
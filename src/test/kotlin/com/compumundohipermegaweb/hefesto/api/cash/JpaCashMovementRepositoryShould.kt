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
    private var cashMovementFound: CashMovement? = null

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

    @Test
    fun `find by id`() {
        givenSpringCashMovementDao()
        givenCashMovementRepository()

        whenFindingByCashMovementId(0L)

        thenTheCashMovementIsSuccessfullyFound()
    }

    @Test
    fun `not find by id`() {
        givenSpringCashMovementDao()
        givenCashMovementRepository()

        whenFindingByCashMovementId(4L)

        thenTheCashMovementIsNotFound()
    }

    private fun givenSpringCashMovementDao() {
        springCashMovementDao = mock()
        `when`(springCashMovementDao.save(CASH_MOVEMENT.toRepresentation())).thenReturn(CASH_MOVEMENT.toRepresentation())
        `when`(springCashMovementDao.findBycashStartEndId(0L)).thenReturn(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT).map { it.toRepresentation() })
        `when`(springCashMovementDao.findById(0L)).thenReturn(Optional.of(CASH_MOVEMENT.toRepresentation()))
        `when`(springCashMovementDao.findById(4L)).thenReturn(Optional.empty())
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

    private fun whenFindingByCashMovementId(cashMovementId: Long) {
        cashMovementFound = cashMovementRepository.findById(cashMovementId)
    }

    private fun thenTheCashMovementIsSuccessfullySaved() {
        verify(springCashMovementDao).save(CASH_MOVEMENT.toRepresentation())
        then(savedCashMovement).isNotNull
    }

    private fun thenTheCashMovementByCashStartEndIdAreSuccessfullyFound() {
        verify(springCashMovementDao).findBycashStartEndId(0L)
        then(cashMovements).isEqualTo(listOf(CASH_MOVEMENT, ANOTHER_CASH_MOVEMENT, AND_ANOTHER_CASH_MOVEMENT))
    }

    private fun thenTheCashMovementIsSuccessfullyFound() {
        verify(springCashMovementDao).findById(0L)
        then(cashMovementFound).isEqualTo(CASH_MOVEMENT)
    }

    private fun thenTheCashMovementIsNotFound() {
        verify(springCashMovementDao).findById(4L)
        then(cashMovementFound).isEqualTo(null)
    }

    private companion object {
        private val CASH_MOVEMENT = CashMovement(0L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
        private val ANOTHER_CASH_MOVEMENT = CashMovement(1L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
        private val AND_ANOTHER_CASH_MOVEMENT = CashMovement(2L, 0L, "", Date(), 0L, "", 0L, 0L,0L, 0.0, "")
    }

    private fun CashMovement.toRepresentation(): CashMovementRepresentation {
        return CashMovementRepresentation(id, cashStartEndId, movementType, dateTime, sourceId, sourceDescription, paymentMethodId, cardId, userId, amount, detail)
    }
}
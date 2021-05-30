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

    @Test
    fun `save a cash movement`() {
        givenSpringCashMovementDao()
        givenCashMovementRepository()

        whenSavingCashMovement()

        thenTheCashMovementIsSuccessfullySaved()
    }

    private fun givenSpringCashMovementDao() {
        springCashMovementDao = mock()
        `when`(springCashMovementDao.save(CASH_MOVEMENT.toRepresentation())).thenReturn(CASH_MOVEMENT.toRepresentation())
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = JpaCashMovementRepository(springCashMovementDao)
    }

    private fun whenSavingCashMovement() {
        savedCashMovement = cashMovementRepository.save(CASH_MOVEMENT)
    }

    private fun thenTheCashMovementIsSuccessfullySaved() {
        verify(springCashMovementDao).save(CASH_MOVEMENT.toRepresentation())
        then(savedCashMovement).isNotNull
    }

    private companion object {
        private val CASH_MOVEMENT = CashMovement(0L, "", Date(), 0L, 0L, 0L,0L, 0.0, "")
    }

    private fun CashMovement.toRepresentation(): CashMovementRepresentation {
        return CashMovementRepresentation(id, movementType, dateTime, transactionId, paymentMethodId, cardId, userId, amount, detail)
    }
}
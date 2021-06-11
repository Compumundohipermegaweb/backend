package com.compumundohipermegaweb.hefesto.api.cash


import com.compumundohipermegaweb.hefesto.api.cash.domain.action.GetTotalMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.TotalMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.TotalMovementRepository
import org.junit.jupiter.api.Test
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.mockito.Mockito.`when`
import java.util.*


class GetTotalMovementShould {
    private lateinit var totalMovementRepository: TotalMovementRepository
    private lateinit var getTotalMovement: GetTotalMovement
    private lateinit var totalsMovements: List<TotalMovement>

    @Test
    fun `get all movement in cash`(){

        givenTotalMovementRepository()
        givenGetTotalMovement()
        thenTotalMovementAreGet()

    }

    private fun givenTotalMovementRepository() {
        totalMovementRepository = mock()
        `when`(totalMovementRepository.findByBranchId(0L)).thenReturn(listOf(TOTAL_MOVEMENT, ANOTHER_TOTAL_MOVEMENT))
    }

    private fun givenGetTotalMovement() {
        getTotalMovement = GetTotalMovement(totalMovementRepository)
        totalsMovements = getTotalMovement.invoke(0L)
    }

    private fun thenTotalMovementAreGet() {
        verify(totalMovementRepository).findByBranchId(0L)
        then(totalsMovements).isEqualTo(listOf(TOTAL_MOVEMENT, ANOTHER_TOTAL_MOVEMENT))
    }

    companion object{
        private val TOTAL_MOVEMENT= TotalMovement(0L,0L,0L, Date(),"INGRESO","Efectivo",100.00)
        private val ANOTHER_TOTAL_MOVEMENT= TotalMovement(0L,0L,0L, Date(),"EGRESO","Efectivo",100.00)
    }
}
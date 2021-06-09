package com.compumundohipermegaweb.hefesto.api.config

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.GetAllAlerts
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository.JpaAlertRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.time.LocalTime

class GetAllAlertsShould {
    private lateinit var jpaAlertRepository: JpaAlertRepository
    private lateinit var getAllAlerts: GetAllAlerts

    private lateinit var allAlerts: List<Alert>

    @Test
    fun `get all alerts`() {
        givenJpaAlertRepository()
        givenGetAllAlerts()

        whenGettingAllAlerts()

        thenTheAlertsAreGet()
    }

    private fun givenJpaAlertRepository() {
        jpaAlertRepository = mock()
        `when`(jpaAlertRepository.getAllAlerts()).thenReturn(listOf(ALERT, ANOTHER_ALERT))
    }

    private fun givenGetAllAlerts() {
        getAllAlerts = GetAllAlerts(jpaAlertRepository)
    }

    private fun whenGettingAllAlerts() {
        allAlerts = getAllAlerts.invoke()
    }

    private fun thenTheAlertsAreGet() {
        verify(jpaAlertRepository).getAllAlerts()
        then(allAlerts).isEqualTo(listOf(ALERT, ANOTHER_ALERT))
    }

    private companion object {
        private val TIME = LocalTime.now()
        private val ALERT = Alert(1L, TIME, "Stock minimo")
        private val ANOTHER_ALERT = Alert(2L, TIME, "Compras a proveedores")
    }
}
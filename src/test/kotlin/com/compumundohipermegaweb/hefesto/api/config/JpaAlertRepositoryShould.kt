package com.compumundohipermegaweb.hefesto.api.config

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository.JpaAlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository.SpringDataAlertDao
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.representation.AlertRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.time.LocalTime

class JpaAlertRepositoryShould {
    private lateinit var springDataAlertDao: SpringDataAlertDao
    private lateinit var jpaAlertRepository: JpaAlertRepository

    private lateinit var alertsFound: List<Alert>

    @Test
    fun `find all alerts`() {
        givenSpringDataAlertDao()
        givenJpaAlertRepository()

        whenFindingAllAlerts()

        thenTheAlertsAreFound()
    }

    private fun givenSpringDataAlertDao() {
        springDataAlertDao = mock()
        `when`(springDataAlertDao.findAll()).thenReturn(mutableListOf(ALERT_REPRESENTATION, ANOTHER_ALERT_REPRESENTATION))
    }

    private fun givenJpaAlertRepository() {
        jpaAlertRepository = JpaAlertRepository(springDataAlertDao)
    }

    private fun whenFindingAllAlerts() {
        alertsFound = jpaAlertRepository.getAllAlerts()
    }

    private fun thenTheAlertsAreFound() {
        verify(springDataAlertDao).findAll()
        then(alertsFound).isEqualTo(listOf(ALERT, ANOTHER_ALERT))
    }

    private companion object {
        private val TIME = LocalTime.now()
        private val ALERT = Alert(1L, TIME, "Stock minimo")
        private val ANOTHER_ALERT = Alert(2L, TIME, "Compras a proveedores")
        private val ALERT_REPRESENTATION = AlertRepresentation(1L, TIME, "Stock minimo")
        private val ANOTHER_ALERT_REPRESENTATION = AlertRepresentation(2L, TIME, "Compras a proveedores")
    }
}
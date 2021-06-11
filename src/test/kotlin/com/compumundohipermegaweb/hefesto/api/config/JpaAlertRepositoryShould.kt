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

class JpaAlertRepositoryShould {
    private lateinit var springDataAlertDao: SpringDataAlertDao
    private lateinit var jpaAlertRepository: JpaAlertRepository

    private lateinit var alertsFound: List<Alert>
    private var alertFound: Alert? = null

    @Test
    fun `find all alerts`() {
        givenSpringDataAlertDao()
        givenJpaAlertRepository()

        whenFindingAllAlerts()

        thenTheAlertsAreFound()
    }

    @Test
    fun `find alerts by description`() {
        givenSpringDataAlertDao()
        givenJpaAlertRepository()

        whenFindingTheAlertByDescription()

        thenTheAlertAreFound()
    }

    @Test
    fun `update alert time`() {
        givenSpringDataAlertDao()
        givenJpaAlertRepository()

        whenUpdatingAlertTime()

        thenTheAlertIsUpdated()
    }

    private fun givenSpringDataAlertDao() {
        springDataAlertDao = mock()
        `when`(springDataAlertDao.findAll()).thenReturn(mutableListOf(ALERT_REPRESENTATION, ANOTHER_ALERT_REPRESENTATION))
        `when`(springDataAlertDao.getByAlertDescription("Stock minimo")).thenReturn(mutableListOf(ALERT_REPRESENTATION))
        `when`(springDataAlertDao.save(ALERT_REPRESENTATION)).thenReturn(UPDATED_ALERT_REPRESENTATION)
    }

    private fun givenJpaAlertRepository() {
        jpaAlertRepository = JpaAlertRepository(springDataAlertDao)
    }

    private fun whenFindingAllAlerts() {
        alertsFound = jpaAlertRepository.getAllAlerts()
    }

    private fun whenFindingTheAlertByDescription() {
        alertFound = jpaAlertRepository.getByProcessDescription("Stock minimo")
    }

    private fun whenUpdatingAlertTime() {
        ALERT.time = "11:00:00"
        alertFound = jpaAlertRepository.updateTimeAlert(ALERT)
    }

    private fun thenTheAlertsAreFound() {
        verify(springDataAlertDao).findAll()
        then(alertsFound).isEqualTo(listOf(ALERT, ANOTHER_ALERT))
    }

    private fun thenTheAlertAreFound() {
        verify(springDataAlertDao).getByAlertDescription("Stock minimo")
        then(alertFound).isEqualTo(ALERT)
    }

    private fun thenTheAlertIsUpdated() {
        verify(springDataAlertDao).getByAlertDescription("Stock minimo")
        verify(springDataAlertDao).save(ALERT_REPRESENTATION)
        then(alertFound).isEqualTo(UPDATED_ALERT)
    }

    private companion object {
        private val ALERT = Alert(1L, "00:00:00", "Stock minimo")
        private val UPDATED_ALERT = Alert(1L, "11:00:00", "Stock minimo")
        private val ANOTHER_ALERT = Alert(2L, "00:00:00", "Compras a proveedores")
        private val ALERT_REPRESENTATION = AlertRepresentation(1L, "00:00:00", "Stock minimo")
        private val UPDATED_ALERT_REPRESENTATION = AlertRepresentation(1L, "11:00:00", "Stock minimo")
        private val ANOTHER_ALERT_REPRESENTATION = AlertRepresentation(2L, "00:00:00", "Compras a proveedores")
    }
}
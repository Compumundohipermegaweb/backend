package com.compumundohipermegaweb.hefesto.api.config

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.UpdateAlert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.request.AlertRequest
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class UpdateAlertShould {
    private lateinit var alertRepository: AlertRepository
    private lateinit var updateAlert: UpdateAlert

    private var alertUpdated: Alert? = null


    @Test
    fun `update alert time`() {
        givenJpaAlertRepository()
        givenUpdateAlert()

        whenUpdatingAlertTime()

        thenTheAlertIsUpdated()
    }

    private fun givenJpaAlertRepository() {
        alertRepository = mock()
        `when`(alertRepository.updateTimeAlert(UPDATED_ALERT)).thenReturn(UPDATED_ALERT)
    }

    private fun givenUpdateAlert() {
        updateAlert = UpdateAlert(alertRepository)
    }


    private fun whenUpdatingAlertTime() {
        alertUpdated = updateAlert.invoke(ALERT_REQUEST)
    }

    private fun thenTheAlertIsUpdated() {
        verify(alertRepository).updateTimeAlert(UPDATED_ALERT)
        then(alertUpdated).isEqualTo(UPDATED_ALERT)
    }

    private companion object {
        private val UPDATED_ALERT = Alert(1L, "11:00:00", "Stock minimo")
        private val ALERT_REQUEST = AlertRequest(1L, "11:00:00", "Stock minimo")
    }
}
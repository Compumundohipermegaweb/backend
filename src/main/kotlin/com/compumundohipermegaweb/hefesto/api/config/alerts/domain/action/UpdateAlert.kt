package com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.request.AlertRequest

class UpdateAlert(private val alertRepository: AlertRepository) {
    operator fun invoke(alertRequest: AlertRequest): Alert? {
        return alertRepository.updateTimeAlert(alertRequest.toAlert())
    }

    private fun AlertRequest.toAlert(): Alert {
        return Alert(id, time, alertDescription)
    }
}





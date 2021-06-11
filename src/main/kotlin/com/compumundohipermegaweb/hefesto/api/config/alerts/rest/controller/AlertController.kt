package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.controller

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.GetAllAlerts
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.UpdateAlert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.request.AlertRequest
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response.AlertResponse
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response.AlertsResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/alerts")
class AlertController(private val getAllAlerts: GetAllAlerts,
                      private val updateAlert: UpdateAlert) {

    @GetMapping
    fun getAllAlerts(): ResponseEntity<AlertsResponse> {
        return ResponseEntity.ok(AlertsResponse(getAllAlerts.invoke().map { it.toAlertResponse() }))
    }

    @PutMapping
    fun updateAlert(@RequestBody alertRequest: AlertRequest): ResponseEntity<AlertResponse> {
        val alert = updateAlert.invoke(alertRequest)
        if(alert != null) {
            return ResponseEntity.ok(alert.toAlertResponse())
        }
        return ResponseEntity.ok(null)
    }

    private fun Alert.toAlertResponse(): AlertResponse {
        return AlertResponse(id, time, alertDescription)
    }
}



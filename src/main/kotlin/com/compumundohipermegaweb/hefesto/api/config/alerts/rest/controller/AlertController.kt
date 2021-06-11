package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.controller

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.GetAllAlerts
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.UpdateAlert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.request.AlertRequest
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response.AlertResponse
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response.AlertsResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/configurable")
class AlertController(private val getAllAlerts: GetAllAlerts,
                      private val updateAlert: UpdateAlert) {

    @GetMapping
    @RequestMapping("/alerts")
    fun getAllAlerts(): ResponseEntity<AlertsResponse> {
        return ResponseEntity.ok(AlertsResponse(getAllAlerts.invoke().map { it.toAlertResponse() }))
    }

    @PostMapping
    @RequestMapping("/alerts/update")
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



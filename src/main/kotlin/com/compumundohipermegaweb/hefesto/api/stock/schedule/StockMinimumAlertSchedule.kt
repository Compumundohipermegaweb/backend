package com.compumundohipermegaweb.hefesto.api.stock.schedule

import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.StockMinimumAlert
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class StockMinimumAlertSchedule(private val stockMinimumAlert: StockMinimumAlert,
                                private val alertRepository: AlertRepository,
                                private val branchRepository: BranchRepository) {

    @Scheduled(cron = "0 40 19 * * ?")
    fun runTask() {

        val branches = branchRepository.findAll()

        branches.forEach {
            var hour = alertRepository.getByProcessDescription("Stock minimo")
            if(hour != null) {
                val scheduleHour = hour.time
                val dateFormat = SimpleDateFormat("HH:mm:ss")
                val actualHour = dateFormat.format(Date()).toString().split(":")[0]
                if(scheduleHour == actualHour){
                    stockMinimumAlert.invoke(it.id)
                }
            }
        }
    }
}
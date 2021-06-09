package com.compumundohipermegaweb.hefesto.api.stock.schedule

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.StockMinimumAlert
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class StockMinimumAlertSchedule(private val stockMinimumAlert: StockMinimumAlert) {

    //@Scheduled(fixedRate = 300000)
    fun runTask() {
        stockMinimumAlert.invoke(1)
    }

}
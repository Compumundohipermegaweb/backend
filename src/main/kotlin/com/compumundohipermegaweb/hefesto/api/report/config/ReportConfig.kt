package com.compumundohipermegaweb.hefesto.api.report.config

import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetOnlineSalesVsLocalSalesData
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ReportConfig {

    @Bean
    fun getOnlineSalesVsLocalSalesData(saleRepository: SaleRepository): GetOnlineSalesVsLocalSalesData {
        return GetOnlineSalesVsLocalSalesData(saleRepository)
    }
}
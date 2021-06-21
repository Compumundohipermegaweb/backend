package com.compumundohipermegaweb.hefesto.api.report.config

import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetOnlineSalesVsLocalSalesData
import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetSalesByBranchData
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ReportConfig {

    @Bean
    fun getOnlineSalesVsLocalSalesData(saleRepository: SaleRepository): GetOnlineSalesVsLocalSalesData {
        return GetOnlineSalesVsLocalSalesData(saleRepository)
    }

    @Bean
    fun getSalesByBranchData(saleRepository: SaleRepository, branchRepository: BranchRepository): GetSalesByBranchData {
        return GetSalesByBranchData(saleRepository, branchRepository)
    }
}
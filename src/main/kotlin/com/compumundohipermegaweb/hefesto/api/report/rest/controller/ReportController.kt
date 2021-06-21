package com.compumundohipermegaweb.hefesto.api.report.rest.controller

import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetOnlineSalesVsLocalSalesData
import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetSalesByBranchData
import com.compumundohipermegaweb.hefesto.api.report.domain.model.OnlineSaleVsLocalSaleData
import com.compumundohipermegaweb.hefesto.api.report.domain.model.SalesByBranchData
import com.compumundohipermegaweb.hefesto.api.report.rest.response.OnlineSaleVsLocalSaleDataResponse
import com.compumundohipermegaweb.hefesto.api.report.rest.response.SalesByBranchDataResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/report")
class ReportController(private val getOnlineSalesVsLocalSalesData: GetOnlineSalesVsLocalSalesData,
                       private val getSalesByBranchData: GetSalesByBranchData) {


    @GetMapping
    @RequestMapping("/ventas/onlinevslocal")
    fun onlineSalesVsLocalSalesReportData(): ResponseEntity<OnlineSaleVsLocalSaleDataResponse> {
        return ResponseEntity.ok(getOnlineSalesVsLocalSalesData.invoke().toOnlineSaleVsLocalSaleDataResponse())
    }

    @GetMapping
    @RequestMapping("/ventasporsucursal")
    fun saleByBranchReportData(): ResponseEntity<SalesByBranchDataResponse> {
        return ResponseEntity.ok(getSalesByBranchData.invoke().toSalesByBranchDataResponse())
    }

    private fun OnlineSaleVsLocalSaleData.toOnlineSaleVsLocalSaleDataResponse(): OnlineSaleVsLocalSaleDataResponse {
        return OnlineSaleVsLocalSaleDataResponse(salesType, salesQuantity, salesAmount)
    }

    private fun SalesByBranchData.toSalesByBranchDataResponse(): SalesByBranchDataResponse {
        return SalesByBranchDataResponse(branches, salesQuantity)
    }
}





package com.compumundohipermegaweb.hefesto.api.report.rest.controller

import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetOnlineSalesVsLocalSalesData
import com.compumundohipermegaweb.hefesto.api.report.domain.model.OnlineSaleVsLocalSaleData
import com.compumundohipermegaweb.hefesto.api.report.rest.response.OnlineSaleVsLocalSaleDataResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/report")
class ReportController(private val getOnlineSalesVsLocalSalesData: GetOnlineSalesVsLocalSalesData) {


    @GetMapping
    @RequestMapping("/ventas/onlinevslocal")
    fun onlineSalesVsLocalSalesReportData(): ResponseEntity<OnlineSaleVsLocalSaleDataResponse> {
        return ResponseEntity.ok(getOnlineSalesVsLocalSalesData.invoke().toResponse())
    }

    private fun OnlineSaleVsLocalSaleData.toResponse(): OnlineSaleVsLocalSaleDataResponse {
        return OnlineSaleVsLocalSaleDataResponse(salesType, salesQuantity, salesAmount)
    }


}



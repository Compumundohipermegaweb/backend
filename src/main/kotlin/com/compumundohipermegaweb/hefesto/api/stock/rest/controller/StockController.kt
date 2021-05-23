package com.compumundohipermegaweb.hefesto.api.stock.rest.controller

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.GetAllStockByBranch
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StockedResponse
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StocksResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/stock")
class StockController(private val getAllStockByBranch: GetAllStockByBranch) {

    @GetMapping
    fun getAllStockByBranch(@RequestParam("branch_id") branchId: Long): ResponseEntity<StocksResponse> {
        return ResponseEntity.ok(StocksResponse(getAllStockByBranch.invoke(branchId).map { it.toStockedResponse() }))
    }

    private fun Stock.toStockedResponse(): StockedResponse {
        return StockedResponse(id, sku, stockTotal, minimumStock, securityStock)
    }
}



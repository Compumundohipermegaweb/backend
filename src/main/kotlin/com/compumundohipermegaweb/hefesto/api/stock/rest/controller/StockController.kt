package com.compumundohipermegaweb.hefesto.api.stock.rest.controller

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockModificationRequest
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockRequest
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StockedResponse
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StocksResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class StockController(private val getAllStockByBranch: GetAllStockByBranch,
                      private val reduceStock: ReduceStock,
                      private val increaseStock: IncreaseStock,
                      private val registerStock: RegisterStock,
                      private val restockRiskItems: RestockRiskItems) {

    @GetMapping("/api/batch/restock")
    fun restock(): ResponseEntity<String> {
        val mail = restockRiskItems().joinToString(" ")
        return ResponseEntity.ok(mail)
    }

    @GetMapping("/api/branches/{branch_id}/stock/all")
    fun getAllStockByBranch(@PathVariable("branch_id") branchId: Long): ResponseEntity<StocksResponse> {
        return ResponseEntity.ok(StocksResponse(getAllStockByBranch.invoke(branchId).map { it.toStockedResponse() }))
    }

    @PostMapping("/api/branches/{branch_id}/stock/decrease")
    fun reduceAllStocks(@RequestBody toReduce: StockModificationRequest, @PathVariable("branch_id") branchId: Long): ResponseEntity<Boolean> {
        reduceStock.invoke(toReduce, branchId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/api/branches/{branch_id}/stock/increase")
    fun increaseAllStocks(@RequestBody toIncrease: StockModificationRequest, @PathVariable("branch_id") branchId: Long): ResponseEntity<Boolean> {
        increaseStock.invoke(toIncrease, branchId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/api/branches/{branch_id}/stock/register")
    fun registerStock(@RequestBody stockRequest: StockRequest, @PathVariable("branch_id") branchId: Long): ResponseEntity<Boolean> {
        registerStock.invoke(stockRequest)
        return ResponseEntity.noContent().build()
    }

    private fun Stock.toStockedResponse(): StockedResponse {
        return StockedResponse(id, sku, stockTotal, minimumStock, securityStock, itemDescription)
    }
}



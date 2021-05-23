package com.compumundohipermegaweb.hefesto.api.stock.rest.controller

import com.compumundohipermegaweb.hefesto.api.stock.domain.action.GetAllStockByBranch
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.IncreaseStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.ReduceStock
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockModificationRequest
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StockedResponse
import com.compumundohipermegaweb.hefesto.api.stock.rest.response.StocksResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/stock")
class StockController(private val getAllStockByBranch: GetAllStockByBranch,
                      private val reduceStock: ReduceStock,
                      private val increaseStock: IncreaseStock) {

    @GetMapping
    fun getAllStockByBranch(@RequestParam("branch_id") branchId: Long): ResponseEntity<StocksResponse> {
        return ResponseEntity.ok(StocksResponse(getAllStockByBranch.invoke(branchId).map { it.toStockedResponse() }))
    }

    @PostMapping
    @RequestMapping("/reduce-all")
    fun reduceAllStocks(@RequestBody toReduce: StockModificationRequest, @RequestParam("branch_id") branchId: Long): ResponseEntity<Boolean> {
        reduceStock.invoke(toReduce, branchId)
        return ResponseEntity.ok(true)
    }

    @PostMapping
    @RequestMapping("/increase-all")
    fun increaseAllStocks(@RequestBody toIncrease: StockModificationRequest, @RequestParam("branch_id") branchId: Long): ResponseEntity<Boolean> {
        increaseStock.invoke(toIncrease, branchId)
        return ResponseEntity.ok(true)
    }

    private fun Stock.toStockedResponse(): StockedResponse {
        return StockedResponse(id, sku, stockTotal, minimumStock, securityStock)
    }
}



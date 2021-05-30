package com.compumundohipermegaweb.hefesto.api.cash.rest.controller

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.OpenCash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.CashResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/cash")
class CashController(private val openCash: OpenCash) {

    @PostMapping
    @RequestMapping("/start")
    fun openCash(@RequestBody openRequest: OpenRequest): ResponseEntity<CashResponse?> {
        val openedCash = openCash.invoke(openRequest)
        if(openedCash != null) {
            return ResponseEntity.ok(openedCash.toResponse())
        }
        return ResponseEntity.ok(null)
    }

    private fun Cash.toResponse(): CashResponse {
        return CashResponse(id, branchId, pointOfSale, status)
    }
}



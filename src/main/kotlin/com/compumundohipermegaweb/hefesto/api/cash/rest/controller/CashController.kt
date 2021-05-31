package com.compumundohipermegaweb.hefesto.api.cash.rest.controller

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CloseRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.CashResponse
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.CashStarEndIdResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/cash")
class CashController(private val openCash: OpenCash,
                     private val registerCash: RegisterCash,
                     private val closeCash: CloseCash,
                     private val getAllRegisterCash: GetAllRegisterCash,
                     private val getCashByUserId: GetCashByUserId) {

    @PostMapping
    @RequestMapping("/start")
    fun openCash(@RequestBody openRequest: OpenRequest): ResponseEntity<CashResponse?> {
        val openedCash = openCash.invoke(openRequest)
        if(openedCash != null) {
            return ResponseEntity.ok(openedCash.toResponse())
        }
        return ResponseEntity.ok(null)
    }

    @PostMapping
    @RequestMapping("/register")
    fun registerCash(@RequestBody cashRequest: CashRequest): ResponseEntity<CashResponse> {
        return ResponseEntity.ok(registerCash.invoke(cashRequest).toResponse())
    }

    @PostMapping
    @RequestMapping("/end")
    fun closeCash(@RequestBody closeRequest: CloseRequest): ResponseEntity<CashResponse> {
        return ResponseEntity.ok(closeCash.invoke(closeRequest)?.toResponse())
    }

    @GetMapping
    @RequestMapping("/all")
    fun getAllRegisterCash(): ResponseEntity<List<CashResponse>> {
        return ResponseEntity.ok(getAllRegisterCash.invoke().map { it.toResponse() })
    }

    @GetMapping
    @RequestMapping("/start-end")
    fun getCashByUserId(@RequestParam("user_id") userId: Long): ResponseEntity<CashStarEndIdResponse> {
        return ResponseEntity.ok(CashStarEndIdResponse(getCashByUserId.invoke(userId)))
    }

    private fun Cash.toResponse(): CashResponse {
        return CashResponse(id, branchId, pointOfSale, status)
    }
}



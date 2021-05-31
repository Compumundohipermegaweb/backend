package com.compumundohipermegaweb.hefesto.api.cash.rest.controller

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CloseRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.CashResponse
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.CashStarEndIdResponse
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.FindAllCashResponse
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.TransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/cash")
class CashController(private val openCash: OpenCash,
                     private val registerCash: RegisterCash,
                     private val closeCash: CloseCash,
                     private val getAllRegisterCash: GetAllRegisterCash,
                     private val getCashByUserId: GetCashByUserId,
                     private val getAllCashMovements: GetAllCashMovements) {

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
    fun getAllRegisterCash(): ResponseEntity<FindAllCashResponse> {
        val cashRegisters = getAllRegisterCash.invoke().map { it.toResponse() }
        return ResponseEntity.ok(FindAllCashResponse(cashRegisters))
    }

    @GetMapping
    @RequestMapping("/start-end")
    fun getCashByUserId(@RequestParam("user_id") userId: Long): ResponseEntity<CashStarEndIdResponse> {
        return ResponseEntity.ok(CashStarEndIdResponse(getCashByUserId.invoke(userId)))
    }

    @GetMapping
    @RequestMapping("transaction/all")
    fun getAllCashMovement(@RequestParam("cash_start_end_id") cashStartEndId: Long): ResponseEntity<List<TransactionResponse>> {
        return ResponseEntity.ok(getAllCashMovements.invoke(cashStartEndId).map { it.toTransaction()})
    }

    private fun Cash.toResponse(): CashResponse {
        return CashResponse(id, branchId, pointOfSale, status)
    }

    private fun CashMovement.toTransaction(): TransactionResponse {
        return TransactionResponse(transactionId, id, transactionDescription, "ACTIVO")
    }
}
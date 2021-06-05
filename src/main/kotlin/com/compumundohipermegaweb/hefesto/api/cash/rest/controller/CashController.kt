package com.compumundohipermegaweb.hefesto.api.cash.rest.controller

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Expense
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Income
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CashRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.CloseRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.request.OpenRequest
import com.compumundohipermegaweb.hefesto.api.cash.rest.response.*
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest
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
                     private val getAllCashMovements: GetAllCashMovements,
                     private val getAllIncomes: GetAllIncomes,
                     private val getAllExpenses: GetAllExpenses,
                     private val updatePaymentDetails: UpdatePaymentDetails) {

    @PostMapping
    @RequestMapping("/start")
    fun openCash(@RequestBody openRequest: OpenRequest): ResponseEntity<CashResponse?> {
        val openedCash = openCash.invoke(openRequest)
        if (openedCash != null) {
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
    fun getAllRegisterCash(): ResponseEntity<CashRegisters> {
        val cashRegisters = getAllRegisterCash.invoke().map { it.toResponse() }
        return ResponseEntity.ok(CashRegisters(cashRegisters))
    }

    @GetMapping
    @RequestMapping("/start-end")
    fun getCashByUserId(@RequestParam("user_id") userId: Long): ResponseEntity<CashStarEndIdResponse> {
        return ResponseEntity.ok(CashStarEndIdResponse(getCashByUserId.invoke(userId)))
    }

    @GetMapping
    @RequestMapping("transaction/all")
    fun getAllCashMovement(@RequestParam("cash_start_end_id") cashStartEndId: Long): ResponseEntity<TransactionsResponse> {
        val transactions = getAllCashMovements.invoke(cashStartEndId).map { it.toTransaction() }
        return ResponseEntity.ok(TransactionsResponse(transactions))
    }

    @GetMapping
    @RequestMapping("cash/income")
    fun getAllCashIncomes(@RequestParam("cash_start_end_id") cashStartEndId: Long): ResponseEntity<IncomesResponse> {
        return ResponseEntity.ok(IncomesResponse(getAllIncomes.invoke(cashStartEndId).map { it.toIncomeResponse() }))
    }

    @GetMapping
    @RequestMapping("cash/expense")
    fun getAllCashExpense(@RequestParam("cash_start_end_id") cashStartEndId: Long): ResponseEntity<ExpensesResponse> {
        return ResponseEntity.ok(ExpensesResponse(getAllExpenses.invoke(cashStartEndId).map { it.toExpenseResponse() }))
    }

    @PostMapping
    @RequestMapping("/payment-details/update")
    fun updateSalePayment(@RequestParam("movement_id") movementId: Long, @RequestBody paymentDetails: List<PaymentRequest>): ResponseEntity<Boolean> {
        updatePaymentDetails.invoke(movementId, paymentDetails)
        return ResponseEntity.ok(true)
    }

    private fun Cash.toResponse(): CashResponse {
        return CashResponse(id, branchId, pointOfSale, status)
    }

    private fun CashMovement.toTransaction(): TransactionResponse {
        return TransactionResponse(sourceId, id, sourceDescription, "ACTIVO")
    }

    private fun Income.toIncomeResponse(): IncomeResponse {
        if(client != null) {
            return IncomeResponse(movement_id, datetime, sourceId, sourceDescription, detail, payments, amount, salesmanId, client.toClientResponse(),transactionId)
        }
        return IncomeResponse(movement_id, datetime,sourceId, sourceDescription, detail, payments, amount, salesmanId, null,transactionId)
    }

    private fun Expense.toExpenseResponse(): ExpenseResponse {
        return ExpenseResponse(movement_id, datetime, transactionDescription, detail, payments, amount)
    }

    private fun Client.toClientResponse(): ClientResponse {
        return ClientResponse(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}



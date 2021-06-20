package com.compumundohipermegaweb.hefesto.api.checking.account.rest.controller

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.action.RegisterCheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.action.UpdateCreditLimit
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.rest.request.CheckingAccountRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientBalanceResponse
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.compumundohipermegaweb.hefesto.api.item.rest.response.ItemResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/checking-account")
class CheckingAccountController (private val registerCheckingAccount: RegisterCheckingAccount,
                                 private val updateCreditLimit: UpdateCreditLimit)
{
    @PostMapping
    fun registerCheckingAccount(@RequestBody checkingAccountRequest: CheckingAccountRequest): ResponseEntity<ClientBalanceResponse> {
        return ResponseEntity.ok(registerCheckingAccount.invoke(checkingAccountRequest.clientId,checkingAccountRequest.creditLimit).toCheckingAccountResponse())
    }

    @PutMapping
    fun updateCreditLimit(@RequestBody checkingAccountRequest: CheckingAccountRequest): ResponseEntity<Boolean> {
        val ok= updateCreditLimit.invoke(checkingAccountRequest)?:false
        return ResponseEntity.ok(ok!=false)
    }

    private fun CheckingAccount.toCheckingAccountResponse(): ClientBalanceResponse {
        return ClientBalanceResponse(id,balance, balanceDue, creditLimit)
    }
}
package com.compumundohipermegaweb.hefesto.api.paymentMethod.rest.controller

import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.paymentMethod.rest.representation.PostPaymentMethodRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/paymentmethods")
class PaymentMethodController (private val registerPaymentMethod: RegisterPaymentMethod){
    @PostMapping
    fun postPaymentMethod(@RequestBody body: PostPaymentMethodRequest): ResponseEntity<PaymentMethod>{
        val paymentMethod = registerPaymentMethod(PaymentMethod(0L,body.paymentMethod,body.state))

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paymentMethod.id).toUri())
            .body(paymentMethod)

    }
}
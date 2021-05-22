package com.compumundohipermegaweb.hefesto.api.payment.method.rest.controller

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.representation.PostPaymentMethodRequest
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
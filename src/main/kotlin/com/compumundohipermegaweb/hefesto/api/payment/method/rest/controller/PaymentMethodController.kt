package com.compumundohipermegaweb.hefesto.api.payment.method.rest.controller

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.FindAllPaymentMethods
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.GetPaymentMethodsByClient
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.request.PostPaymentMethodRequest
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.response.GetAllPaymentMethodResponse
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.response.PaymentMethodResponse
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.response.PaymentMethodsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api")
class PaymentMethodController (private val registerPaymentMethod: RegisterPaymentMethod,
                               private val getPaymentMethodsByClient: GetPaymentMethodsByClient,
                               private val findAllPaymentMethods: FindAllPaymentMethods){

    @GetMapping("/payment-methods")
    fun getAllPaymentMethods(): ResponseEntity<PaymentMethodsResponse> {
        val paymentMethods = findAllPaymentMethods().map { it.toPaymentMethodResponse() }
        return ResponseEntity.ok(PaymentMethodsResponse(paymentMethods))
    }

    @PostMapping
    fun postPaymentMethod(@RequestBody body: PostPaymentMethodRequest): ResponseEntity<PaymentMethod>{
        val paymentMethod = registerPaymentMethod(PaymentMethod(0L,body.paymentMethod,body.state, body.type))

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paymentMethod.id).toUri())
            .body(paymentMethod)

    }

    @GetMapping("/clients/{CLIENT_ID}/payment-methods")
    fun paymentMethodByClient(@PathVariable(name = "CLIENT_ID") clientId: Long): ResponseEntity<PaymentMethodsResponse> {
        val paymentMethods = getPaymentMethodsByClient.invoke(clientId)
        return ResponseEntity.ok(PaymentMethodsResponse(paymentMethods.map { it.toPaymentMethodResponse() }))
    }

    private fun PaymentMethod.toPaymentMethodResponse(): PaymentMethodResponse {
        return PaymentMethodResponse(id, type, description, state)
    }
}



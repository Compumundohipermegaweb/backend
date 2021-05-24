package com.compumundohipermegaweb.hefesto.api.online.sale.rest.controller

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.online.sale.domain.action.ProcessOnlineSale
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/online-sale")
class OnlineSaleController(private val processOnlineSale: ProcessOnlineSale) {

    @PostMapping
    fun processOnlineSale(@RequestBody onlineSale: SaleRequest): ResponseEntity<Invoice> {
        return ResponseEntity.ok(processOnlineSale.invoke(onlineSale))
    }
}
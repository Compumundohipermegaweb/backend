package com.compumundohipermegaweb.hefesto.api.order.rest.controller

import com.compumundohipermegaweb.hefesto.api.order.domain.action.GetAllOrders
import com.compumundohipermegaweb.hefesto.api.order.domain.model.Order
import com.compumundohipermegaweb.hefesto.api.order.rest.response.OrderResponse
import com.compumundohipermegaweb.hefesto.api.order.rest.response.OrdersResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/order")
class OrderController(private val getAllOrders: GetAllOrders) {

    /*@GetMapping
    fun processOnlineSale(@RequestParam("branch_id") branchId: Long): ResponseEntity<OrdersResponse> {
        return ResponseEntity.ok(OrdersResponse(getAllOrders.invoke(branchId).map { it.toOrderResponse() }))
    }

    private fun Order.toOrderResponse(): OrderResponse {
        return OrderResponse(id, saleId, state, shippingPrice, shippingCompany)
    }*/
}



package com.compumundohipermegaweb.hefesto.api.order.rest.controller

import com.compumundohipermegaweb.hefesto.api.order.domain.action.GetAllOrders
import com.compumundohipermegaweb.hefesto.api.order.domain.action.UpdateOrderState
import com.compumundohipermegaweb.hefesto.api.order.domain.model.OrderWhitItemDetails
import com.compumundohipermegaweb.hefesto.api.order.rest.response.OrderWhitDetailsResponse
import com.compumundohipermegaweb.hefesto.api.order.rest.response.OrdersWhitDetailsResponse
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailsResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleItemDetailResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/order")
class OrderController(private val getAllOrders: GetAllOrders,
                      private val updateOrderState: UpdateOrderState) {

    @GetMapping
    fun getAllOrders(@RequestParam("branch_id") branchId: Long): ResponseEntity<OrdersWhitDetailsResponse> {
        return ResponseEntity.ok(OrdersWhitDetailsResponse(getAllOrders.invoke(branchId).map { it.toOrderWhitDetailsResponse()}))
    }

    @PutMapping
    fun updateOrderState(@RequestParam("order_id") orderId: Long): ResponseEntity<Boolean> {
        updateOrderState.invoke(orderId)
        return ResponseEntity.ok(true)
    }

    private fun OrderWhitItemDetails.toOrderWhitDetailsResponse(): OrderWhitDetailsResponse {

        val itemDetailsResponse = mutableListOf<SaleItemDetailResponse>()

        saleItemDetails.map {
            itemDetailsResponse+=it.toSaleDetailsResponse()
        }

        return OrderWhitDetailsResponse(id, saleId, state, shippingPrice, shippingCompany, SaleDetailsResponse(itemDetailsResponse))
    }

    private fun SaleDetail.toSaleDetailsResponse(): SaleItemDetailResponse {
        return SaleItemDetailResponse(id, description, quantity, unitPrice)
    }
}








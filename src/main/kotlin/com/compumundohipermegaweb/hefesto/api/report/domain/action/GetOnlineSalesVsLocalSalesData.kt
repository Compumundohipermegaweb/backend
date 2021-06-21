package com.compumundohipermegaweb.hefesto.api.report.domain.action

import com.compumundohipermegaweb.hefesto.api.report.domain.model.OnlineSaleVsLocalSaleData
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetOnlineSalesVsLocalSalesData(private val saleRepository: SaleRepository) {
    operator fun invoke(): OnlineSaleVsLocalSaleData {
        val sales = saleRepository.findAll()
        val localSale = sales.filter { it.category == "VENTA LOCAL" }
        val onlineSale = sales.filter { it.category == "VENTA ONLINE" }
        val amountOfLocalSales = localSale.map { it.total }.reduce { acc, d -> acc + d }
        val amountOfOnlineSales = onlineSale.map { it.total }.reduce { acc, d -> acc + d }
        val quantityOfLocaleSale = localSale.size
        val quantityOfOnlineSale = onlineSale.size
        val salesType = listOf("VENTA LOCAL", "VENTA ONLINE")
        val salesAmount = listOf(amountOfLocalSales, amountOfOnlineSales)
        val salesQuantity = listOf(quantityOfLocaleSale, quantityOfOnlineSale)
        return OnlineSaleVsLocalSaleData(salesType, salesQuantity, salesAmount)
    }
}
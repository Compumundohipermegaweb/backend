package com.compumundohipermegaweb.hefesto.api.report.domain.action

import com.compumundohipermegaweb.hefesto.api.report.domain.model.OnlineSaleVsLocalSaleData
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetOnlineSalesVsLocalSalesData(private val saleRepository: SaleRepository) {
    operator fun invoke(): OnlineSaleVsLocalSaleData {
        val sales = saleRepository.findAll()
        val localSale = sales.filter { it.category == "LOCAL" }
        val onlineSale = sales.filter { it.category == "VENTA_ONLINE" }
        val amountOfLocalSales = calculateAmountOfLocalSales(localSale)
        val amountOfOnlineSales = calculateAmountOfOnlineSales(onlineSale)
        val quantityOfLocaleSale = localSale.size
        val quantityOfOnlineSale = onlineSale.size
        val salesType = listOf("VENTA LOCAL", "VENTA ONLINE")
        val salesAmount = listOf(amountOfLocalSales, amountOfOnlineSales)
        val salesQuantity = listOf(quantityOfLocaleSale, quantityOfOnlineSale)
        return OnlineSaleVsLocalSaleData(salesType, salesQuantity, salesAmount)
    }

    private fun calculateAmountOfLocalSales(localSale: List<Sale>): Double {
        return if (localSale.isNotEmpty()) {
            localSale.map { it.total }.reduce { acc, d -> acc + d }
        } else {
            0.0
        }
    }

    private fun calculateAmountOfOnlineSales(onlineSale: List<Sale>): Double {
        return if (onlineSale.isNotEmpty()) {
            onlineSale.map { it.total }.reduce { acc, d -> acc + d }
        } else {
            0.0
        }
    }
}
package com.compumundohipermegaweb.hefesto.api.report.domain.action

import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.report.domain.model.SalesByBranchData
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository

class GetSalesByBranchData(private val saleRepository: SaleRepository,
                           private val branchRepository: BranchRepository) {
    operator fun invoke(): SalesByBranchData {
        val quantityOfSales = mutableListOf<Int>()
        val branches = branchRepository.findAll()
        branches.forEach {
            val sales = saleRepository.findByBranchId(it.id)
            quantityOfSales+=sales.size
        }
        return SalesByBranchData(branches, quantityOfSales)
    }
}
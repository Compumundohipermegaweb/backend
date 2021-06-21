package com.compumundohipermegaweb.hefesto.api.report.domain.model

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch

data class SaleForBranchData(val branches: List<Branch>,
                             val saleQuantity: List<Int>)
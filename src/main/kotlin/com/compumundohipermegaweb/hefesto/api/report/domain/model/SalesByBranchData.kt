package com.compumundohipermegaweb.hefesto.api.report.domain.model

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch

data class SalesByBranchData(val branches: List<Branch>,
                             val salesQuantity: List<Int>)
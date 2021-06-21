package com.compumundohipermegaweb.hefesto.api.report.domain.model

data class OnlineSaleVsLocalSaleData(val salesType: List<String>,
                                     val salesQuantity: List<Int>,
                                     val salesAmount: List<Double>)

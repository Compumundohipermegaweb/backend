package com.compumundohipermegaweb.hefesto.api.report

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetOnlineSalesVsLocalSalesData
import com.compumundohipermegaweb.hefesto.api.report.domain.model.OnlineSaleVsLocalSaleData
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetOnlineSalesVsLocalSalesDataShould {
    private lateinit var saleRepository: SaleRepository
    private lateinit var getOnlineSaleVsLocalSaleData: GetOnlineSalesVsLocalSalesData

    private lateinit var reportData: OnlineSaleVsLocalSaleData


    @Test
    fun `generate report data`() {
        givenSaleRepository()
        givenOnlineSaleVsLocalSaleData()

        whenGenerateReportData()

        thenTheReportDataIsSuccessfullyGenerated()
    }


    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.findAll()).thenReturn(listOf(LOCAL_SALE_FOR_REPORTS, ANOTHER_LOCAL_SALE_FOR_REPORTS, ONLINE_SALE_FOR_REPORTS, ANOTHER_ONLINE_SALE_FOR_REPORTS))
    }

    private fun givenOnlineSaleVsLocalSaleData() {
        getOnlineSaleVsLocalSaleData = GetOnlineSalesVsLocalSalesData(saleRepository)
    }

    private fun whenGenerateReportData() {
        reportData = getOnlineSaleVsLocalSaleData.invoke()
    }

    private fun thenTheReportDataIsSuccessfullyGenerated() {
        verify(saleRepository).findAll()
        then(reportData).isEqualTo(EXPECTED_REPORT_DATA)
    }

    private companion object {
        val DEFAULT_CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val LOCAL_SALE_FOR_REPORTS = Sale(1L, "B", DEFAULT_CLIENT, 0L, 0L, SaleDetails(ArrayList(), ArrayList(), null), 150.0, "LOCAL")
        val ANOTHER_LOCAL_SALE_FOR_REPORTS = Sale(2L, "B", DEFAULT_CLIENT, 0L, 0L, SaleDetails(ArrayList(), ArrayList(), null), 150.0, "LOCAL")
        val ONLINE_SALE_FOR_REPORTS = Sale(3L, "B", DEFAULT_CLIENT, 0L, 0L, SaleDetails(ArrayList(), ArrayList(), null), 250.0, "VENTA_ONLINE")
        val ANOTHER_ONLINE_SALE_FOR_REPORTS = Sale(4L, "B", DEFAULT_CLIENT, 0L, 0L, SaleDetails(ArrayList(), ArrayList(), null), 250.0, "VENTA_ONLINE")
        val EXPECTED_REPORT_DATA = OnlineSaleVsLocalSaleData(listOf("VENTA LOCAL", "VENTA ONLINE"), listOf(2, 2), listOf(300.0, 500.0))
    }
}
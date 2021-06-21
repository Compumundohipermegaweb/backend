package com.compumundohipermegaweb.hefesto.api.report

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.report.domain.action.GetSalesForBranchData
import com.compumundohipermegaweb.hefesto.api.report.domain.model.SaleForBranchData
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetSalesForBranchDataShould {
    private lateinit var saleRepository: SaleRepository
    private lateinit var branchRepository: BranchRepository
    private lateinit var getSalesForBranchData: GetSalesForBranchData

    private lateinit var reportData: SaleForBranchData


    @Test
    fun `generate report data`() {
        givenSaleRepository()
        givenBranchRepository()
        givenGetSalesForBranchData()

        whenGenerateReportData()

        thenTheReportDataIsSuccessfullyGenerated()
    }


    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.findByBranchId(1L)).thenReturn(listOf(SALE_FOR_REPORTS_BRANCH_UNO, ANOTHER_SALE_FOR_REPORTS_BRANCH_UNO))
        `when`(saleRepository.findByBranchId(2L)).thenReturn(listOf(SALE_FOR_REPORTS_BRANCH_DOS, ANOTHER_SALE_FOR_REPORTS_BRANCH_DOS))
    }

    private fun givenBranchRepository() {
        branchRepository = mock()
        `when`(branchRepository.findAll()).thenReturn(listOf(BRANCH_UNO, BRANCH_DOS))
    }

    private fun givenGetSalesForBranchData() {
        getSalesForBranchData = GetSalesForBranchData(saleRepository, branchRepository)
    }

    private fun whenGenerateReportData() {
        reportData = getSalesForBranchData.invoke()
    }

    private fun thenTheReportDataIsSuccessfullyGenerated() {
        verify(branchRepository).findAll()
        verify(saleRepository).findByBranchId(1L)
        verify(saleRepository).findByBranchId(2L)
        then(reportData).isEqualTo(EXPECTED_REPORT_DATA)
    }

    private companion object {
        val BRANCH_UNO = Branch(1L,"SUC01","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
        val BRANCH_DOS = Branch(2L,"SUC02","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
        val DEFAULT_CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val SALE_FOR_REPORTS_BRANCH_UNO = Sale(1L, "B", DEFAULT_CLIENT, 0L, 1L, SaleDetails(ArrayList(), ArrayList()), 150.0, "LOCAL")
        val ANOTHER_SALE_FOR_REPORTS_BRANCH_UNO = Sale(2L, "B", DEFAULT_CLIENT, 0L, 1L, SaleDetails(ArrayList(), ArrayList()), 150.0, "LOCAL")
        val SALE_FOR_REPORTS_BRANCH_DOS = Sale(3L, "B", DEFAULT_CLIENT, 0L, 2L, SaleDetails(ArrayList(), ArrayList()), 250.0, "LOCAL")
        val ANOTHER_SALE_FOR_REPORTS_BRANCH_DOS = Sale(4L, "B", DEFAULT_CLIENT, 0L, 2L, SaleDetails(ArrayList(), ArrayList()), 250.0, "LOCAL")
        val EXPECTED_REPORT_DATA = SaleForBranchData(listOf(BRANCH_UNO, BRANCH_DOS), listOf(2, 2))
    }
}
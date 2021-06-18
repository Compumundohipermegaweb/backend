package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.service.SupplierService
import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMailMessage
import javax.mail.internet.MimeMessage

class RestockRiskItemsShould {

    private lateinit var stockRepository: StockRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var mailSender: JavaMailSender
    private lateinit var supplierService: SupplierService
    private lateinit var restockRiskItems: RestockRiskItems

    @Test
    fun `obtain items with low stock`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenLowStockWasConsulted()
    }

    @Test
    fun `create purchase orders`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenPurchaseOrdersHasBeenCreated()
    }

    @Test
    fun `check if the item already has purchase order`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenPurchaseOrdersHaveBeenChecked()
    }

    @Test
    fun `only create purchase order for items that does not have one already`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenPurchaseOrdersHasBeenCreated()
        butNoForItemsWithExistingPurchaseOrders()
    }

    @Test
    fun `send the purchase orders to the suppliers`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenEmailsWhereSent()
    }

    @Test
    fun `load the supplier emails`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierService()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        verify(supplierService, times(AMOUNT_ITEMS_WITHOUT_PURCHASE_ORDER)).findBySuppliedSku(any())
    }

    private fun givenSupplierService() {
        supplierService = mock()
        `when`(supplierService.findBySuppliedSku("0")).thenReturn(SUPPLIER_1)
        `when`(supplierService.findBySuppliedSku("1")).thenReturn(SUPPLIER_2)
    }

    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.findLowStock()).thenReturn(LOW_STOCK)
    }

    private fun givenMailSender() {
        mailSender = mock()
        `when`(mailSender.createMimeMessage()).thenReturn(MIME_MESSAGE)
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
        `when`(purchaseOrderRepository.save(ORDER_1)).thenReturn(ORDER_1)
        `when`(purchaseOrderRepository.save(ORDER_2)).thenReturn(ORDER_2)
        `when`(purchaseOrderRepository.exists(LOW_STOCK[2].sku)).thenReturn(true)
    }

    private fun givenRestockRiskItems() {
        restockRiskItems = RestockRiskItems(stockRepository, purchaseOrderRepository, mailSender, supplierService)
    }

    private fun whenRestockingRiskItems() {
        restockRiskItems()
    }

    private fun thenLowStockWasConsulted() {
        verify(stockRepository).findLowStock()
    }

    private fun thenPurchaseOrdersHasBeenCreated() {
        verify(purchaseOrderRepository).save(ORDER_1)
        verify(purchaseOrderRepository).save(ORDER_2)
    }

    private fun butNoForItemsWithExistingPurchaseOrders() {
        verify(purchaseOrderRepository, never()).save(ORDER_3)
    }

    private fun thenPurchaseOrdersHaveBeenChecked() {
        LOW_STOCK.forEach {
            verify(purchaseOrderRepository).exists(it.sku)
        }
    }

    private fun thenEmailsWhereSent() {
        verify(mailSender, times(AMOUNT_ITEMS_WITHOUT_PURCHASE_ORDER))
    }

    private companion object {
        val LOW_STOCK = listOf(
                Stock(0, "0", 1, 7, 5, 10, ""),
                Stock(1, "1", 1, 14, 10, 20, ""),
                Stock(2, "2", 1, 14, 10, 15, ""))

        val AMOUNT_ITEMS_WITHOUT_PURCHASE_ORDER = LOW_STOCK.size - 1
        val ORDER_1 = PurchaseOrder(0, 1L, "0", 10, 0, 0.0, "supplier1@gmail.com", PurchaseOrder.Status.PENDING, 0L)
        val ORDER_2 = PurchaseOrder(0, 1L, "1", 20, 0, 0.0, "supplier2@gmail.com", PurchaseOrder.Status.PENDING, 0L)
        val ORDER_3 = PurchaseOrder(0, 1L, "2", 15, 0, 0.0, "supplier3@gmail.com", PurchaseOrder.Status.PENDING, 0L)
        val SUPPLIER_1 = Supplier(0L, "", "", "", "supplier1@gmail.com", "")
        val SUPPLIER_2 = Supplier(0L, "", "", "", "supplier2@gmail.com", "")
        val MIME_MESSAGE = mock<MimeMessage>().also { `when`(it.content).thenReturn("") }
    }
}
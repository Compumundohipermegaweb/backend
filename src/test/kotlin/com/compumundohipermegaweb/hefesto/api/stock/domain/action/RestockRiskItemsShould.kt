package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.mail.javamail.JavaMailSender

class RestockRiskItemsShould {

    private lateinit var stockRepository: StockRepository
    private lateinit var purchaseOrderRepository: PurchaseOrderRepository
    private lateinit var mailSender: JavaMailSender
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var restockRiskItems: RestockRiskItems

    @Test
    fun `obtain items with low stock`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierRepository()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenLowStockWasConsulted()
    }

    @Test
    fun `create purchase orders`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierRepository()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenPurchaseOrdersHasBeenCreated()
    }

    @Test
    fun `check if the item already has purchase order`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierRepository()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenPurchaseOrdersHaveBeenChecked()
    }

    @Test
    fun `only create purchase order for items that does not have one already`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierRepository()
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
        givenSupplierRepository()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        thenEmailsWhereSent()
    }

    @Test
    fun `load the supplier emails`() {
        givenStockRepository()
        givenPurchaseOrderRepository()
        givenMailSender()
        givenSupplierRepository()
        givenRestockRiskItems()

        whenRestockingRiskItems()

        verify(supplierRepository, times(AMOUNT_ITEMS_WITHOUT_PURCHASE_ORDER)).findBySupplySku(any())
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock()
        `when`(supplierRepository.findBySupplySku("0")).thenReturn(SUPPLIER_1)
        `when`(supplierRepository.findBySupplySku("1")).thenReturn(SUPPLIER_2)
    }

    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.findLowStock()).thenReturn(LOW_STOCK)
    }

    private fun givenMailSender() {
        mailSender = mock()
        `when`(mailSender.createMimeMessage()).thenReturn(mock())
    }

    private fun givenPurchaseOrderRepository() {
        purchaseOrderRepository = mock()
        `when`(purchaseOrderRepository.save(ORDER_1)).thenReturn(ORDER_1)
        `when`(purchaseOrderRepository.save(ORDER_2)).thenReturn(ORDER_2)
        `when`(purchaseOrderRepository.exists(LOW_STOCK[2].sku)).thenReturn(true)
    }

    private fun givenRestockRiskItems() {
        restockRiskItems = RestockRiskItems(stockRepository, purchaseOrderRepository, mailSender, supplierRepository)
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
        val ORDER_1 = PurchaseOrder(0, "0", 10, "supplier1@gmail.com")
        val ORDER_2 = PurchaseOrder(0, "1", 20, "supplier2@gmail.com")
        val ORDER_3 = PurchaseOrder(0, "2", 15, "supplier3@gmail.com")
        val SUPPLIER_1 = Supplier(0L, "", "", "", "supplier1@gmail.com", "", "0")
        val SUPPLIER_2 = Supplier(0L, "", "", "", "supplier2@gmail.com", "", "1")
    }
}
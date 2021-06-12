package com.compumundohipermegaweb.hefesto.api.item

import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockRequest
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.PostSupplierRequest
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RegisterItemShould {

    private lateinit var registerItem: RegisterItem
    private lateinit var itemService: ItemService
    private lateinit var supplierRepository: SupplierRepository
    private lateinit var registeredItem: Item

    @Test
    fun `register a item`(){
        givenItemRepository()
        givenSupplierRepository()
        givenRegisterItem()

        whenRegisteringItem()

        thenTheItemIsSuccessfullyRegister()
    }

    @Test
    fun `return registered item`(){
        givenItemRepository()
        givenSupplierRepository()
        givenRegisterItem()

        whenRegisteringItem()

        thenTheItemRegisteredIsSuccessfullyReturned()
    }

    private fun givenItemRepository() {
        itemService = mock(ItemService::class.java)
        `when`(itemService.save(ITEM)).thenReturn(ITEM)
    }

    private fun givenSupplierRepository() {
        supplierRepository = mock(SupplierRepository::class.java)
        `when`(supplierRepository.save(SUPPLIER)).thenReturn(SUPPLIER)
    }

    private fun givenRegisterItem() {
       registerItem = RegisterItem(itemService, supplierRepository)
    }

    private fun whenRegisteringItem() {
        registeredItem = registerItem(ITEM_REQUEST)
    }

    private fun thenTheItemIsSuccessfullyRegister() {
        verify(itemService).save(ITEM)
    }

    private fun thenTheItemRegisteredIsSuccessfullyReturned() {
        then(registerItem).isNotNull
    }

    private companion object {
        private val ITEM = Item(0L, "", "", "", 0L, 0L, "", 0.0, 10.0, true, "", 0)
        private val SUPPLIER_REQUEST = PostSupplierRequest("", "", "", "", "", "")
        private val SUPPLIER = Supplier(0L, "", "", "" ,"", "", "")
        private val ITEM_REQUEST = ItemRequest("", "", "",  0L, 0L, "", 0.0, 10.0, true, "", SUPPLIER_REQUEST)
    }
}
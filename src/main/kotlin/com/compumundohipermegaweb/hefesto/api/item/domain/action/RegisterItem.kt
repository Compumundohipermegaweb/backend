package com.compumundohipermegaweb.hefesto.api.item.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.compumundohipermegaweb.hefesto.api.stock.rest.request.StockRequest
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository
import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.PostSupplierRequest

class RegisterItem(private val itemService: ItemService,
                    private val supplierRepository: SupplierRepository) {

    operator fun invoke(item: ItemRequest): Item {

        val savedItem = itemService.save(item.toItem())
         saveSupplier(item.supplier)

        return savedItem
    }

    private fun saveSupplier(supplierRequest: PostSupplierRequest): Long {
        return supplierRepository.save(supplierRequest.toSupplier()).id
    }

    private fun ItemRequest.toItem(): Item {
        return Item(0L, sku, shortDescription, description, brandId, categoryId, uomSale, price, cost, imported, state, 0)
    }

    private fun PostSupplierRequest.toSupplier(): Supplier {
        return Supplier(0L, organization, contactName, contactNumber, email, cuit)
    }
}





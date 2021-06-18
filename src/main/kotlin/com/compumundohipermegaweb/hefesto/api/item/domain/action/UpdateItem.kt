package com.compumundohipermegaweb.hefesto.api.item.domain.action


import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest

class UpdateItem(private val itemService: ItemService){
    operator fun invoke(itemRequest: ItemRequest): Item? {
        val itemFromDB = itemService.findItemById(itemRequest.id)

        return if (itemFromDB != null) {
            val item = itemRequest.toItemRepresentation(itemFromDB)
            itemService.save(item.toItem(itemFromDB))
        }else{
            null
        }
    }
    private fun ItemRequest.toItemRepresentation(item: Item) = ItemRepresentation(id,sku,shortDescription,description,item.brandId,categoryId,uomSale,price,cost,imported,state)
    private fun ItemRepresentation.toItem(item: Item) = Item(id,sku,shortDescription,description,item.brandId,categoryId,uomSale,price,cost,imported,state,item.availableStock)
}
package com.compumundohipermegaweb.hefesto.api.item.domain.action


import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.item.infra.representation.ItemRepresentation
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest

class UpdateItem(private val itemService: ItemService){
    operator fun invoke(itemRequest: ItemRequest): Item? {
        val itemFromDB = itemService.findItemById(itemRequest.id)

        return if (itemFromDB != null) {
            val item = itemRequest.toItemRepresentation()
            itemService.save(item.toItem())
        }else{
            null
        }
    }
    private fun ItemRequest.toItemRepresentation() = ItemRepresentation(id,sku,shortDescription,description,brandId,categoryId,uomSale,price,cost,imported,state)
    private fun ItemRepresentation.toItem() = Item(id,sku,shortDescription,description,brandId,categoryId,uomSale,price,cost,imported,state,0)
}
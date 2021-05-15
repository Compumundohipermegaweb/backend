package com.compumundohipermegaweb.hefesto.api.item.rest

import com.compumundohipermegaweb.hefesto.api.item.domain.action.GetItemsByShortDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.compumundohipermegaweb.hefesto.api.item.rest.response.ItemResponse
import com.compumundohipermegaweb.hefesto.api.item.rest.response.ItemsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/items")
class ItemController(private val registerItem: RegisterItem,
                     private val getItemsByShortDescription: GetItemsByShortDescription) {

    @PostMapping
    fun registerItem(@RequestBody itemRequest: ItemRequest): ResponseEntity<ItemResponse> {
        return ResponseEntity.ok(registerItem.invoke(itemRequest).toItemResponse())
    }

    @GetMapping
    fun getAllItemsByShortDescription(@RequestParam shortDescription: String): ResponseEntity<ItemsResponse> {
        var list: List<Item> = getItemsByShortDescription.invoke(shortDescription)
        var listResponse: List<ItemResponse> = ArrayList()

        for(item in list) {
            listResponse+=item.toItemResponse()
        }

        return ResponseEntity.ok(ItemsResponse(listResponse))
    }

    private fun Item.toItemResponse(): ItemResponse {
        return ItemResponse(sku, shortDescription, description, brandId, categoryId, uomSale, price, imported, state)
    }
}
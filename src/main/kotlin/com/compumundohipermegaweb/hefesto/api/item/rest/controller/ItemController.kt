package com.compumundohipermegaweb.hefesto.api.item.rest.controller

import com.compumundohipermegaweb.hefesto.api.item.domain.action.*
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.rest.request.ItemRequest
import com.compumundohipermegaweb.hefesto.api.item.rest.response.ItemResponse
import com.compumundohipermegaweb.hefesto.api.item.rest.response.ItemsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/items")
class ItemController(private val registerItem: RegisterItem,
                     private val getItemsByDescription: GetItemsByDescription,
                     private val getAllItems: GetAllItems,
                     private val deleteItem: DeleteItem,
                     private val updateItem: UpdateItem) {

    @PostMapping
    fun registerItem(@RequestBody itemRequest: ItemRequest): ResponseEntity<ItemResponse> {
        return ResponseEntity.ok(registerItem.invoke(itemRequest).toItemResponse())
    }

    @GetMapping
    fun getAllItemsByShortDescription(@RequestParam description: String): ResponseEntity<ItemsResponse> {
        var list: List<Item> = getItemsByDescription.invoke(description)
        var listResponse: List<ItemResponse> = ArrayList()

        for(item in list) {
            listResponse+=item.toItemResponse()
        }

        return ResponseEntity.ok(ItemsResponse(listResponse))
    }

    @GetMapping
    @RequestMapping("/all")
    fun getAllItems(): ResponseEntity<ItemsResponse> =
        ResponseEntity.ok(ItemsResponse(getAllItems.invoke().map { it.toItemResponse() }))

    @DeleteMapping("/{sku}")
    fun deleteItemBySku(@PathVariable("sku") sku: String): ResponseEntity<String> {
        deleteItem(sku)
        return ResponseEntity.noContent().build()
    }

    @PutMapping
    fun updateItem(@RequestBody itemRequest: ItemRequest): ResponseEntity<ItemResponse> {
        return ResponseEntity.ok(updateItem.invoke(itemRequest)?.toItemResponse())
    }

    private fun Item.toItemResponse(): ItemResponse {
        return ItemResponse(id, sku, shortDescription, description, categoryId, uomSale, price, cost, imported, state)
    }
}
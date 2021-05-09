package com.compumundohipermegaweb.hefesto.api.item.rest

import com.compumundohipermegaweb.hefesto.api.item.domain.action.FindAllItemByShortDescription
import com.compumundohipermegaweb.hefesto.api.item.domain.action.RegisterItem
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/items")
class ItemController(private val registerItem: RegisterItem,
                     private val findAllItemByShortDescription: FindAllItemByShortDescription) {

    @PostMapping
    fun postItem(@RequestBody itemRequest: ItemRequest): ResponseEntity<ItemResponse> {
        return ResponseEntity.ok(registerItem(itemRequest.toItem()).toItemResponse())
    }

    @GetMapping
    fun getAllItemsByShortDescription(@RequestParam shortDescription: String): ResponseEntity<ItemsResponse> {
        var list: List<Item> = findAllItemByShortDescription(shortDescription)
        var listResponse: List<ItemResponse> = ArrayList()

        for(item in list) {
            listResponse+=item.toItemResponse()
        }

        return ResponseEntity.ok(ItemsResponse(listResponse))
    }

    private fun ItemRequest.toItem(): Item {
        return Item(0L, "", shortDescription, longDescription, measure, stockTotal, minimumStock, securityStock, supplier, cost, unitPrice)
    }

    private fun Item.toItemResponse(): ItemResponse {
        return ItemResponse(id, sku, shortDescription, longDescription, measure, stockTotal, minimumStock, securityStock, supplier, cost, unitPrice)
    }
}
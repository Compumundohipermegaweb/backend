package com.compumundohipermegaweb.hefesto.api.branch.rest.controller

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.ItemStockResponse
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.PostBranchRequest
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.StockResponse
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemStock
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@RequestMapping("/api/branches")
class BranchController (private val registerBranch: RegisterBranch,
                        private val findStockedItems: FindStockedItems) {
    @PostMapping
    fun postBranch (@RequestBody body: PostBranchRequest) : ResponseEntity <Branch> {
        val branch = registerBranch((Branch(0L,body.branch, body.address, body.postalCode,body.email,body.contactNumber,body.attentionSchedule)))

        return  ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(branch.id).toUri())
                              .body(branch);
    }

    @GetMapping("/{BRANCH_ID}/stock")
    fun getStock(@PathVariable("BRANCH_ID") branchId: Long,
                 @RequestParam("category_id") categoryId: Long?,
                 @RequestParam("description") description: String?,
                 @RequestParam("brand_id") brandId: Long?,
                 @RequestParam("imported") imported: Boolean?,): ResponseEntity<StockResponse> {
        val searchCriteria = SearchCriteria(branchId, categoryId, description, brandId, imported)
        val items = findStockedItems(searchCriteria)
        val itemResponse = items.map { it.toItemStockResponse() }

        //return ResponseEntity.ok(StockResponse(itemResponse))
        return ResponseEntity.ok(StockResponse(listOf(
                ItemStockResponse(1,"1", "Martillo", "Martillo de carpintero", "Redline", 1250.0, 20, true),
                ItemStockResponse(2,"2", "Martillo", "Martillo de mango largo", "", 1322.99, 34, true),
                ItemStockResponse(3,"3", "Martillo", "Martillo de mango corto", "", 1450.0, 21, true),
                ItemStockResponse(4,"4", "Martillo", "Martillo nashe", "", 1200.99, 30, true),
                ItemStockResponse(5,"5", "Martillo", "Martillito re loco", "", 989.99, 6, true)
        )))
    }

    private fun ItemStock.toItemStockResponse(): ItemStockResponse {
        return ItemStockResponse(id, sku, shortDescription, longDescription, brandName, price, availableStock, imported)
    }
}





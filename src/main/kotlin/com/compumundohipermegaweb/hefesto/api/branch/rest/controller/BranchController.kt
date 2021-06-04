package com.compumundohipermegaweb.hefesto.api.branch.rest.controller

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindAllBranches
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.GetStockAvailable
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.ItemStockResponse
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.PostBranchRequest
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.StockResponse
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.*
import com.compumundohipermegaweb.hefesto.api.item.domain.model.ItemStock
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@RequestMapping("/api/branches")
class BranchController (private val registerBranch: RegisterBranch,
                        private val findStockedItems: FindStockedItems,
                        private val getStockAvailable: GetStockAvailable,
                        private val findAllBranches: FindAllBranches) {
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

        return ResponseEntity.ok(StockResponse(itemResponse))
    }

    @GetMapping
    fun getAll(): ResponseEntity<GetAllBranchesResponse> {
        val branches = findAllBranches().map { it.toResponse() }
        return ResponseEntity.ok(GetAllBranchesResponse(branches))
    }

    @GetMapping("/{BRANCH_ID}/stock/{SKU}")
    fun getStockAvailable (@PathVariable("BRANCH_ID") branchId: Long,
                           @PathVariable("SKU") sku: String): ResponseEntity<StockAvailableResponse> {
        val stock = getStockAvailable(sku,branchId)
        if (stock != null) {
            return ResponseEntity.ok(StockAvailableResponse(stock.sku, stock.stockTotal))
        }
        return  ResponseEntity.ok(StockAvailableResponse(sku,0))
    }

    private fun Branch.toResponse() = BranchResponse(id, branch, address, postalCode, email, contactNumber, attentionSchedule)

    private fun ItemStock.toItemStockResponse(): ItemStockResponse {
        return ItemStockResponse(id, sku, shortDescription, longDescription, brandName, price, availableStock, imported)
    }


}





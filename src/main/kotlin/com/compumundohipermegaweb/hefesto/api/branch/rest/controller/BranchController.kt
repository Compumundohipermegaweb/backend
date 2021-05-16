package com.compumundohipermegaweb.hefesto.api.branch.rest.controller

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.FindStockedItems
import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.SearchCriteria
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.GetStockRequest
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.PostBranchRequest
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.StockResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
                 @RequestBody body: GetStockRequest): ResponseEntity<StockResponse> {
        val searchCriteria = SearchCriteria(branchId, body.categoryId, body.description, body.brandId, body.imported)
        val items = findStockedItems(searchCriteria)
        return ResponseEntity.ok(null)
    }
}
package com.compumundohipermegaweb.hefesto.api.branch.rest.controller

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.PostBranchRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@RestController
@RequestMapping("/api/branches")
class BranchController (private val registerBranch: RegisterBranch) {
    @PostMapping
    fun postBranch (@RequestBody body: PostBranchRequest) : ResponseEntity <Branch> {
        val branch = registerBranch((Branch(0L,body.branch, body.address, body.postalCode,body.email,body.contactNumber,body.attentionSchedule)))

        return  ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(branch.id).toUri())
                              .body(branch);
    }
}
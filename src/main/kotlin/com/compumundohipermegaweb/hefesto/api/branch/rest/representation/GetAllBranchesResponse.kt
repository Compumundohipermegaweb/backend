package com.compumundohipermegaweb.hefesto.api.branch.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllBranchesResponse(@JsonProperty("branches") val branches: List<BranchResponse>)

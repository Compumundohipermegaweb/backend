package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllDispatchesResponse(
        @JsonProperty("dispatches") val dispatches: List<FullDispatchResponse>)

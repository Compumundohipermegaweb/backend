package com.compumundohipermegaweb.hefesto.api.branch.domain.model

data class SearchCriteria(
        val branch: Long,
        val category: Long?,
        val description: String?,
        val brand: Long?,
        val imported: Boolean?)

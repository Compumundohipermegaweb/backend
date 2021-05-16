package com.compumundohipermegaweb.hefesto.api.item.domain.model

data class SearchCriteria(
        val branch: Long,
        val category: Long?,
        val description: String?,
        val brand: Long?,
        val imported: Boolean?)

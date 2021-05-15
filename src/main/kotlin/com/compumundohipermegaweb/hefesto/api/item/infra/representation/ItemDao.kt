package com.compumundohipermegaweb.hefesto.api.item.infra.representation

import javax.persistence.*

@Entity
@Table(name = "ITEM")
data class ItemDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID")val id: Long,
                   @Column(name = "SKU") val sku: String,
                   @Column(name = "SHORT_DESCRIPTION") val shortDescription: String,
                   @Column(name = "DESCRIPTION") val description: String,
                   @Column(name = "BRAND_ID") val brandId: Long,
                   @Column(name = "CATEGORY_ID") val categoryId: Long,
                   @Column(name = "UOM_SALE") val uomSale: String,
                   @Column(name = "PRICE") val price: Double,
                   @Column(name = "IMPORTED")val imported: Boolean,
                   @Column(name = "STATE") val state: String)
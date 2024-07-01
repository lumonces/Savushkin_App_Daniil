package com.example.savushkin.data.mappers

import com.example.savushkin.data.ProductOfRequestEntity
import com.example.savushkin.domain.models.ProductOfRequest

fun ProductOfRequestEntity.toDomain() = ProductOfRequest(
    codeProduct = codeProduct,
    quantity = quantity,
    numberRequest = numberRequest
)

fun List<ProductOfRequestEntity>.toDomain() = map {it.toDomain()}
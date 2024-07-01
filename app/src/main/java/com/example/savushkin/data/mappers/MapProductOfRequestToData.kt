package com.example.savushkin.data.mappers

import com.example.savushkin.data.DirectoryEntity
import com.example.savushkin.data.ProductOfRequestEntity
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest

fun ProductOfRequest.toData() = ProductOfRequestEntity(
    codeProduct = codeProduct,
    quantity = quantity,
    numberRequest = numberRequest
)
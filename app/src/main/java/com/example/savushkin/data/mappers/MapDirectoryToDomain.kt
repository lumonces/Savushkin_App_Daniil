package com.example.savushkin.data.mappers

import com.example.savushkin.data.DirectoryEntity
import com.example.savushkin.data.ProductOfRequestEntity
import com.example.savushkin.domain.models.Directory

fun DirectoryEntity.toDomain() = Directory(
    codeProduct = codeProduct,
    nameProduct = nameProduct,
    temperature = temperature,
    ean13 = ean13
)

fun List<DirectoryEntity>.toDomain() = map {it.toDomain()}
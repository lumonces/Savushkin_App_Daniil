package com.example.savushkin.data.mappers

import com.example.savushkin.data.DirectoryEntity
import com.example.savushkin.data.RequestEntity
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.Request

fun Directory.toData() = DirectoryEntity(
    codeProduct = codeProduct,
    nameProduct = nameProduct,
    temperature = temperature,
    ean13 = ean13
)
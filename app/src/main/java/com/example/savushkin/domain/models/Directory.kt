package com.example.savushkin.domain.models

data class Directory (
    var codeProduct: String = "0",
    var nameProduct: String = "",
    var temperature: Double = 0.0,
    var ean13: String = ""
)
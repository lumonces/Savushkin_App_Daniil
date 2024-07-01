package com.example.savushkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Directory")
data class DirectoryEntity(
    @PrimaryKey
    var codeProduct: String = "0",
    var nameProduct: String = "",
    var temperature: Double = 0.0,
    var ean13: String = ""
)
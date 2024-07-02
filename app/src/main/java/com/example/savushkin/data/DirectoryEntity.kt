package com.example.savushkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Directory")
data class DirectoryEntity(
    @PrimaryKey
    var codeProduct: String = "0",
    var nameProduct: String = "",
    var temperature: String = "",
    var ean13: String = ""
)
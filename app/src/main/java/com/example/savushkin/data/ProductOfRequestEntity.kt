package com.example.savushkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ProductOfRequest",
    primaryKeys = ["codeProduct", "numberRequest"]
)
data class ProductOfRequestEntity(
    var codeProduct : String,
    var quantity : Int,
    var numberRequest : Long
)
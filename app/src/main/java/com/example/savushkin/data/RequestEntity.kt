package com.example.savushkin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Request")
data class RequestEntity(
    @PrimaryKey
    var numberRequest: Long = 0,
    var dateRequest: String = "",
    var nameShop: String = "",
    var addressShop: String = "",
    var statusRequest : String = ""
)
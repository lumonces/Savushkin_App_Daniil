package com.example.savushkin.domain.models

data class Request(
    var numberRequest: Long = 0,
    var dateRequest: String = "",
    var nameShop: String = "",
    var addressShop: String = "",
    var statusRequest : String = ""
)
package com.example.savushkin.data.mappers

import com.example.savushkin.data.RequestEntity
import com.example.savushkin.domain.models.Request

fun Request.toData() = RequestEntity(
    numberRequest = numberRequest,
    dateRequest = dateRequest,
    nameShop = nameShop,
    addressShop = addressShop,
    statusRequest = statusRequest
)
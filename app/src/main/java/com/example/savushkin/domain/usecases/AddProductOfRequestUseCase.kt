package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.repository.MyRepository

class AddProductOfRequestUseCase(private val myRep : MyRepository) {

    suspend fun execute(product : ProductOfRequest) {
        myRep.addProductForRequest(product = product)
    }
}
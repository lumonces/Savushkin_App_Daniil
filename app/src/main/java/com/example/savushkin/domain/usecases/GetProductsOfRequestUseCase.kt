package com.example.savushkin.domain.usecases

import androidx.lifecycle.LiveData
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.repository.MyRepository

class GetProductsOfRequestUseCase(private val myRep : MyRepository) {

    suspend fun execute(numberRequest : Long) : List<ProductOfRequest> {
        return myRep.getAllProductByRequest(numberRequest = numberRequest)
    }
}
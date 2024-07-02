package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class GetCountProductsOfRequestsUseCase(private val myRep : MyRepository) {

    suspend fun execute() : Int {
        return myRep.getCountProductsOfRequests()
    }
}
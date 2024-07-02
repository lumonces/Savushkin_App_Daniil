package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class GetCountRequestUseCase(private val myRep : MyRepository) {

    suspend fun execute() : Int {
        return myRep.getCountRequests()
    }
}
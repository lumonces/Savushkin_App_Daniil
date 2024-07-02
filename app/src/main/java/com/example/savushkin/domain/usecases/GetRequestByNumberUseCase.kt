package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

class GetRequestByNumberUseCase(private val myRep : MyRepository) {

    suspend fun execute(number : Long) : Request {
        return myRep.getRequestByNumber(number)
    }
}
package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

class AddRequestUseCase(private val myRep : MyRepository) {

    suspend fun execute(request : Request) {
        myRep.addRequest(request)
    }
}
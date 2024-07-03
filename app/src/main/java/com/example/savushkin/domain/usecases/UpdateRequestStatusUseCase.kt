package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class UpdateRequestStatusUseCase(private val myRep : MyRepository) {

    suspend fun execute(numberRequest : Long, newStatusRequest : String) {
        myRep.updateRequestStatus(
            numberRequest = numberRequest,
            newStatusRequest = newStatusRequest
        )
    }
}
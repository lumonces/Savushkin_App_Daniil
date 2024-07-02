package com.example.savushkin.domain.usecases

import androidx.lifecycle.LiveData
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

class GetAllRequestsUseCase(private val myRep : MyRepository) {

    fun execute() : LiveData<List<Request>> {
        return myRep.getAllRequests()
    }
}
package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class GetCorrectPasswordUseCase(private val myRep : MyRepository) {

    fun execute() = myRep.getCorrectPassword()
}
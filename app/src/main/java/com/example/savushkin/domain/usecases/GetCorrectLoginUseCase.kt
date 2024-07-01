package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class GetCorrectLoginUseCase(private val myRep : MyRepository) {

    fun execute() = myRep.getCorrectLogin()
}
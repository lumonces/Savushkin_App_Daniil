package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class GetStatusRememberEnterUseCase(private val myRep : MyRepository) {

    fun execute() = myRep.getStatusRememberEnter()
}
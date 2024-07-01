package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class SetStatusRememberEnterUseCase(private val myRep : MyRepository) {

    fun execute(newStatus : Boolean) {
        myRep.setStatusRememberEnter(newStatus)
    }
}
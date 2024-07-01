package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class CheckAuthUseCase(private val myRep : MyRepository) {

    fun execute(login : String, password : String) : Boolean {
        return myRep.getLogin() == login && myRep.getPassword() == password
    }
}
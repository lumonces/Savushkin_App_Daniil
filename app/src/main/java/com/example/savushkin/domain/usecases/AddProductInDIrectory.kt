package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.repository.MyRepository

class AddProductInDirectory(private val myRep : MyRepository) {

    suspend fun execute(product : Directory) {
        myRep.addProductInDirectory(product)
    }
}
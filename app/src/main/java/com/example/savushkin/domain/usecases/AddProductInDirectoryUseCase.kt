package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

class AddProductInDirectoryUseCase(private val myRep : MyRepository) {

    suspend fun execute(product : Directory) {
        myRep.addProductInDirectory(product)
    }
}
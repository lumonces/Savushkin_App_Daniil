package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.repository.MyRepository

class GetProductOfDirectoryByCodeUseCase(private val myRep : MyRepository) {

    suspend fun execute(code : String) : List<Directory> {
        return myRep.getProductOfDirectoryByCode(code = code)
    }
}
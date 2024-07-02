package com.example.savushkin.domain.usecases

import androidx.lifecycle.LiveData
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.repository.MyRepository


class GetProductsOfDirectoryUseCase(private val myRep : MyRepository) {

    fun execute() : LiveData<List<Directory>> {
        return myRep.getAllProductOfDirectory()
    }
}
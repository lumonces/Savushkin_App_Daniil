package com.example.savushkin.domain.usecases

import com.example.savushkin.domain.repository.MyRepository

class UpdateQuantityOfProductUseCase(private val myRep : MyRepository) {

    suspend fun execute(codeProduct : String, newQuantity : Int, numberRequest : Long) {
        myRep.updateQuantityOfProduct(
            codeProduct = codeProduct,
            newQuantity = newQuantity,
            numberRequest = numberRequest
        )
    }
}
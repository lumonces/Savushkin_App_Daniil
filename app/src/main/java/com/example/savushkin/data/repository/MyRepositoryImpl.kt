package com.example.savushkin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.savushkin.data.MyDAO
import com.example.savushkin.data.mappers.toData
import com.example.savushkin.data.mappers.toDomain
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

class MyRepositoryImpl (
    private val myDAO: MyDAO
) : MyRepository {

    override suspend fun addRequest(request : Request) {
        myDAO.addRequest(request.toData())
    }

    override fun getAllRequests() : LiveData<List<Request>> {
        return myDAO.getAllRequests().map {
            it.toDomain()
        }
    }

    override suspend fun getRequestByNumber(numberRequest: Long) : Request {
        return myDAO.getRequestByNumber(numberRequest = numberRequest).toDomain()
    }

    override suspend fun updateRequestStatus(numberRequest : Long, newStatusRequest : String) {
        myDAO.updateRequestStatus(numberRequest = numberRequest, newStatusRequest = newStatusRequest)
    }

    override suspend fun addProductForRequest(product : ProductOfRequest) {
        myDAO.addProductForRequest(product = product.toData())
    }

    override suspend fun updateQuantityOfProduct(
        codeProduct : String, newQuantity : Double, numberRequest : Long
    ) {
        myDAO.updateQuantityOfProduct(
            codeProduct = codeProduct,
            newQuantity = newQuantity,
            numberRequest = numberRequest
        )
    }

    override fun getAllProductByRequest(numberRequest : Long) : LiveData<List<ProductOfRequest>> {
        return myDAO.getAllProductByRequest(numberRequest = numberRequest).map {
            it.toDomain()
        }
    }

    override fun getAllProductOfDirectory() : LiveData<List<Directory>> {
        return myDAO.getAllProductOfDirectory().map {
            it.toDomain()
        }
    }
}
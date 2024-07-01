package com.example.savushkin.domain.repository

import androidx.lifecycle.LiveData
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.models.Request

interface MyRepository {

    suspend fun addRequest(request : Request)

    fun getAllRequests() : LiveData<List<Request>>

    suspend fun getRequestByNumber(numberRequest: Long) : Request

    suspend fun updateRequestStatus(numberRequest : Long, newStatusRequest : String)

    suspend fun addProductForRequest(product : ProductOfRequest)

    suspend fun updateQuantityOfProduct(codeProduct : String, newQuantity : Double, numberRequest : Long)

    fun getAllProductByRequest(numberRequest : Long) : LiveData<List<ProductOfRequest>>

    fun getAllProductOfDirectory() : LiveData<List<Directory>>

    fun loadRequestFromXML()

    fun getLogin() : String

    fun getCorrectLogin() : String

    fun getPassword() : String

    fun getCorrectPassword() : String

    fun getStatusRememberEnter() : Boolean

    fun setStatusRememberEnter(newStatus : Boolean)
}
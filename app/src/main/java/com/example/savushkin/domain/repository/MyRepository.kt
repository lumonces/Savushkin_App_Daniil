package com.example.savushkin.domain.repository

import androidx.lifecycle.LiveData
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.models.Request

interface MyRepository {

    suspend fun addRequest(request : Request)

    suspend fun addProductInDirectory(product : Directory)

    fun getAllRequests() : LiveData<List<Request>>

    suspend fun getRequestByNumber(numberRequest: Long) : Request

    suspend fun updateRequestStatus(numberRequest : Long, newStatusRequest : String)

    suspend fun addProductForRequest(product : ProductOfRequest)

    suspend fun updateQuantityOfProduct(codeProduct : String, newQuantity : Double, numberRequest : Long)

    suspend fun getAllProductByRequest(numberRequest : Long) : List<ProductOfRequest>

    fun getAllProductOfDirectory() : LiveData<List<Directory>>

    fun getLogin() : String

    fun getCorrectLogin() : String

    fun getPassword() : String

    fun getCorrectPassword() : String

    fun getStatusRememberEnter() : Boolean

    fun setStatusRememberEnter(newStatus : Boolean)

    suspend fun getCountInDirectory() : Int

    suspend fun getCountRequests() : Int

    suspend fun getCountProductsOfRequests() : Int

    suspend fun getProductOfDirectoryByCode(code : String) : List<Directory>
}
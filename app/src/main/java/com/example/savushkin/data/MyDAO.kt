package com.example.savushkin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface MyDAO {

    @Insert
    suspend fun addRequest(request : RequestEntity)

    @Insert
    suspend fun addProductInDirectory(product : DirectoryEntity)

    @Query("SELECT * FROM Request")
    fun getAllRequests() : LiveData<List<RequestEntity>>
    @Query("SELECT * FROM Request WHERE numberRequest = :numberRequest")
    suspend fun getRequestByNumber(numberRequest: Long) : RequestEntity

    @Query("UPDATE Request SET statusRequest = :newStatusRequest WHERE numberRequest = :numberRequest")
    suspend fun updateRequestStatus(numberRequest : Long, newStatusRequest : String)

    @Insert
    suspend fun addProductForRequest(product : ProductOfRequestEntity)

    @Query("UPDATE ProductOfRequest SET quantity = :newQuantity WHERE codeProduct = :codeProduct AND numberRequest = :numberRequest")
    suspend fun updateQuantityOfProduct(codeProduct : String, newQuantity : Double, numberRequest : Long)

    @Query("SELECT * FROM ProductOfRequest WHERE numberRequest = :numberRequest")
    fun getAllProductByRequest(numberRequest : Long) : LiveData<List<ProductOfRequestEntity>>

    @Query("SELECT * FROM Directory")
    fun getAllProductOfDirectory() : LiveData<List<DirectoryEntity>>

    @Query("SELECT COUNT(*) FROM Directory")
    suspend fun getCountInDirectory() : Int
}
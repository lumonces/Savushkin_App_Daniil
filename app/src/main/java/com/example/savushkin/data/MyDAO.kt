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

    @Insert
    suspend fun addProductForRequest(product : ProductOfRequestEntity)


    @Query("SELECT * FROM Request")
    fun getAllRequests() : LiveData<List<RequestEntity>>
    @Query("SELECT * FROM Request WHERE numberRequest = :numberRequest")
    suspend fun getRequestByNumber(numberRequest: Long) : RequestEntity

    @Query("SELECT * FROM ProductOfRequest WHERE numberRequest = :numberRequest")
    suspend fun getAllProductsByRequest(numberRequest : Long) : List<ProductOfRequestEntity>

    @Query("SELECT * FROM Directory")
    fun getAllProductOfDirectory() : LiveData<List<DirectoryEntity>>

    @Query("SELECT COUNT(*) FROM Directory")
    suspend fun getCountInDirectory() : Int

    @Query("SELECT COUNT(*) FROM Request")
    suspend fun getCountRequests() : Int

    @Query("SELECT COUNT(*) FROM ProductOfRequest")
    suspend fun getCountProductsOfRequests() : Int

    @Query("SELECT * FROM Directory WHERE codeProduct = :code")
    suspend fun getProductOfDirectoryByCode(code : String) : List<DirectoryEntity>


    @Query("UPDATE Request SET statusRequest = :newStatusRequest WHERE numberRequest = :numberRequest")
    suspend fun updateRequestStatus(numberRequest : Long, newStatusRequest : String)

    @Query("UPDATE ProductOfRequest SET quantity = :newQuantity WHERE codeProduct = :codeProduct AND numberRequest = :numberRequest")
    suspend fun updateQuantityOfProduct(codeProduct : String, newQuantity : Double, numberRequest : Long)
}
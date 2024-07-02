package com.example.savushkin.data.repository

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.savushkin.data.MyDAO
import com.example.savushkin.data.mappers.toData
import com.example.savushkin.data.mappers.toDomain
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.repository.MyRepository

private const val SHARED_PREFS = "shared_prefs"
private const val KEY_LOGIN = "login"
private const val KEY_PASSWORD = "password"
private const val KEY_STATUS_REMEMBER_ENTER = "checkbox"
private const val CORRECT_LOGIN = "qwe"
private const val CORRECT_PASSWORD = "123"
private const val CURRENT_STATUS_REMEMBER_ENTER = false

class MyRepositoryImpl (
    private val myDAO: MyDAO,
    context : Context
) : MyRepository {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    init {
        sharedPrefs.edit {
            if (!sharedPrefs.contains(KEY_LOGIN)) {
                putString(KEY_LOGIN, CORRECT_LOGIN)
            }
            if (!sharedPrefs.contains(KEY_PASSWORD)) {
                putString(KEY_PASSWORD, CORRECT_PASSWORD)
            }
            if(!sharedPrefs.contains(KEY_STATUS_REMEMBER_ENTER)) {
                putBoolean(KEY_STATUS_REMEMBER_ENTER, CURRENT_STATUS_REMEMBER_ENTER)
            }
            apply()
        }
    }

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

    override fun getLogin(): String {
        return sharedPrefs.getString(KEY_LOGIN, "") ?: ""
    }

    override fun getPassword(): String {
        return sharedPrefs.getString(KEY_PASSWORD, "") ?: ""
    }

    override fun getStatusRememberEnter(): Boolean {
        return sharedPrefs.getBoolean(KEY_STATUS_REMEMBER_ENTER, false)
    }

    override fun setStatusRememberEnter(newStatus: Boolean) {
        sharedPrefs.edit {
            if(sharedPrefs.contains(KEY_STATUS_REMEMBER_ENTER)) {
                putBoolean(KEY_STATUS_REMEMBER_ENTER, newStatus)
            }
            apply()
        }
    }

    override fun getCorrectLogin(): String {
        return sharedPrefs.getString(KEY_LOGIN, "") ?: ""
    }

    override fun getCorrectPassword(): String {
        return sharedPrefs.getString(KEY_PASSWORD, "") ?: ""
    }

    override suspend fun getCountInDirectory(): Int {
        return myDAO.getCountInDirectory()
    }

    override suspend fun getCountRequests(): Int {
        return myDAO.getCountRequests()
    }

    override suspend fun addProductInDirectory(product: Directory) {
        myDAO.addProductInDirectory(product.toData())
    }
}
package com.example.savushkin.presentation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var stateNumberRequest by mutableLongStateOf(0)
    private var stateDateRequest by mutableStateOf("")
    private var stateNameShopRequest by mutableStateOf("")
    private var stateAddressShopRequest by mutableStateOf("")
    private var stateStatusRequest by mutableStateOf("")

    fun getNumberRequest() : Long {
        return stateNumberRequest
    }

    fun getDateRequest() : String {
        return stateDateRequest
    }

    fun getNameShopRequest() : String {
        return stateNameShopRequest
    }

    fun getAddressShopRequest() : String {
        return stateAddressShopRequest
    }

    fun getStatusRequest() : String {
        return stateStatusRequest
    }

    fun setNumberRequest(newNumberRequest : Long) {
        stateNumberRequest = newNumberRequest
    }

    fun setDateRequest(newDateRequest : String) {
        stateDateRequest = newDateRequest
    }

    fun setNameShopRequest(newNameShopRequest : String) {
        stateNameShopRequest = newNameShopRequest
    }

    fun setAddressShopRequest(newAddressShopRequest : String) {
        stateAddressShopRequest = newAddressShopRequest
    }

    fun setStatusRequest(newStatusRequest : String) {
        stateStatusRequest = newStatusRequest
    }
}
package com.example.savushkin.presentation

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.savushkin.data.MyRoom
import com.example.savushkin.data.repository.MyRepositoryImpl
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.usecases.CheckAuthUseCase
import com.example.savushkin.domain.usecases.GetCorrectLoginUseCase
import com.example.savushkin.domain.usecases.GetCorrectPasswordUseCase
import com.example.savushkin.domain.usecases.GetStatusRememberEnterUseCase
import com.example.savushkin.domain.usecases.SetStatusRememberEnterUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val requestsList : LiveData<List<Request>>
    private var myRep : MyRepositoryImpl

    // СОСТОЯНИЯ АВТОРИЗАЦИИ
    private var stateLogin by mutableStateOf("")
    private var statePassword by mutableStateOf("")
    private var stateStatusRememberEnter by mutableStateOf(false)

    // СОСТОЯНИЯ ЗАЯВКИ
    private var stateNumberRequest by mutableLongStateOf(0)
    private var stateDateRequest by mutableStateOf("")
    private var stateNameShopRequest by mutableStateOf("")
    private var stateAddressShopRequest by mutableStateOf("")
    private var stateStatusRequest by mutableStateOf("")

    // USECASE-ы
    private val checkAuthUseCase: CheckAuthUseCase
    private val getStatusRememberEnterUseCase: GetStatusRememberEnterUseCase
    private val setStatusRememberEnterUseCase: SetStatusRememberEnterUseCase
    private val getCorrectLoginUseCase: GetCorrectLoginUseCase
    private val getCorrectPasswordUseCase: GetCorrectPasswordUseCase

    init {
        val myDao = MyRoom.getDataBase(application).Dao()
        myRep = MyRepositoryImpl(
            myDAO = myDao,
            context = application.applicationContext
        )
        requestsList = myRep.getAllRequests()

        checkAuthUseCase = CheckAuthUseCase(myRep = myRep)
        getStatusRememberEnterUseCase = GetStatusRememberEnterUseCase(myRep = myRep)
        setStatusRememberEnterUseCase = SetStatusRememberEnterUseCase(myRep = myRep)
        getCorrectLoginUseCase = GetCorrectLoginUseCase(myRep = myRep)
        getCorrectPasswordUseCase = GetCorrectPasswordUseCase(myRep = myRep)

        if(getStatusRememberEnterUseCase.execute()) {
            stateLogin = getCorrectLoginUseCase.execute()
            statePassword = getCorrectPasswordUseCase.execute()
            stateStatusRememberEnter = getStatusRememberEnterUseCase.execute()
        }
    }
    fun checkAuth(navigateToAllRequestsPage : () -> Unit, toast : Toast) {
        if(checkAuthUseCase.execute(login = stateLogin, password = statePassword)) {
            setStatusRememberEnterUseCase.execute(stateStatusRememberEnter)
            navigateToAllRequestsPage()
        }
        else {
            toast.show()
        }
    }

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

    fun getLogin() : String {
        return stateLogin
    }

    fun getPassword() : String {
        return statePassword
    }

    fun getStatusRememberEnter() : Boolean {
        return stateStatusRememberEnter
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

    fun setLogin(newLogin : String) {
        stateLogin = newLogin
    }

    fun setPassword(newPassword : String) {
        statePassword = newPassword
    }

    fun setStatusRememberEnter(newStatus : Boolean) {
        stateStatusRememberEnter = newStatus
    }
}
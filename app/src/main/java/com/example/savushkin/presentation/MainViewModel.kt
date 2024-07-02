package com.example.savushkin.presentation

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.savushkin.data.MyRoom
import com.example.savushkin.data.repository.MyRepositoryImpl
import com.example.savushkin.domain.XMLObject
import com.example.savushkin.domain.XMLParser
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.usecases.CheckAuthUseCase
import com.example.savushkin.domain.usecases.GetAllRequestsUseCase
import com.example.savushkin.domain.usecases.GetCorrectLoginUseCase
import com.example.savushkin.domain.usecases.GetCorrectPasswordUseCase
import com.example.savushkin.domain.usecases.GetCountInDirectoryUseCase
import com.example.savushkin.domain.usecases.GetProductsOfDirectoryUseCase
import com.example.savushkin.domain.usecases.GetStatusRememberEnterUseCase
import com.example.savushkin.domain.usecases.SetStatusRememberEnterUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val requestsList : LiveData<List<Request>>
    val directoryList : LiveData<List<Directory>>
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
    private val getCountInDirectoryUseCase: GetCountInDirectoryUseCase
    private val getAllRequestsUseCase: GetAllRequestsUseCase
    private val getProductsOfDirectoryUseCase: GetProductsOfDirectoryUseCase

    init {
        val myDao = MyRoom.getDataBase(application).Dao()
        myRep = MyRepositoryImpl(
            myDAO = myDao,
            context = application.applicationContext
        )

        checkAuthUseCase = CheckAuthUseCase(myRep = myRep)
        getStatusRememberEnterUseCase = GetStatusRememberEnterUseCase(myRep = myRep)
        setStatusRememberEnterUseCase = SetStatusRememberEnterUseCase(myRep = myRep)
        getCorrectLoginUseCase = GetCorrectLoginUseCase(myRep = myRep)
        getCorrectPasswordUseCase = GetCorrectPasswordUseCase(myRep = myRep)
        getCountInDirectoryUseCase = GetCountInDirectoryUseCase(myRep = myRep)
        getAllRequestsUseCase = GetAllRequestsUseCase(myRep = myRep)
        getProductsOfDirectoryUseCase = GetProductsOfDirectoryUseCase(myRep = myRep)

        if(getStatusRememberEnterUseCase.execute()) {
            stateLogin = getCorrectLoginUseCase.execute()
            statePassword = getCorrectPasswordUseCase.execute()
            stateStatusRememberEnter = getStatusRememberEnterUseCase.execute()
        }

        runBlocking {
            if(getCountInDirectoryUseCase.execute() == 0) {
                loadDataInDirectory()
            }


        }

        requestsList = getAllRequestsUseCase.execute()
        directoryList = getProductsOfDirectoryUseCase.execute()
    }

    private fun loadDataInDirectory() {
        viewModelScope.launch {
            val directoryList = getDirectoryFromXML()
            directoryList.forEach {
                myRep.addProductInDirectory(it)
            }
        }
    }

    private fun getDirectoryFromXML() : List<Directory> {
        val xmlFile = "directory.xml"
        val assetManager = getApplication<Application>().assets
        val inputStream = assetManager.open(xmlFile)
        val xmlObject = XMLParser.parse(inputStream)
        val directoryList = ArrayList<Directory>()

        xmlObject.descendants?.forEach { directoryObject ->
            if (directoryObject.tag == "NS_MC") {
                val codeProduct = directoryObject.get("KMC")?.value ?: ""
                val gradus = (directoryObject.get("GRADUS")?.value ?: "")
                val nameProduct = directoryObject.get("SNM")?.value ?: ""
                val ean13 = directoryObject.get("EAN13")?.value ?: ""
                directoryList.add(Directory(
                    codeProduct = codeProduct,
                    nameProduct = nameProduct,
                    temperature = gradus,
                    ean13 = ean13
                ))
            }
        }
        return directoryList
    }

    private fun getAllDirectoryObjects(xmlObject: XMLObject, tagName : String) : List<Directory> {
        val directoryList = ArrayList<Directory>()

        xmlObject.descendants?.forEach { directoryObject ->
            if (directoryObject.tag == tagName) {
                val codeProduct = directoryObject.get("KMC")?.value ?: ""
                val gradus = (directoryObject.get("GRADUS")?.value ?: "")
                val nameProduct = directoryObject.get("SNM")?.value ?: ""
                val ean13 = directoryObject.get("EAN13")?.value ?: ""
                directoryList.add(Directory(
                    codeProduct = codeProduct,
                    nameProduct = nameProduct,
                    temperature = gradus,
                    ean13 = ean13
                ))
            }
        }
        return directoryList
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
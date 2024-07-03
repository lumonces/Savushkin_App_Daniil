package com.example.savushkin.presentation

import android.app.Application
import android.view.Gravity
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
import com.example.savushkin.domain.XMLParser
import com.example.savushkin.domain.models.Directory
import com.example.savushkin.domain.models.ProductOfRequest
import com.example.savushkin.domain.models.Request
import com.example.savushkin.domain.usecases.AddProductInDirectoryUseCase
import com.example.savushkin.domain.usecases.AddProductOfRequestUseCase
import com.example.savushkin.domain.usecases.AddRequestUseCase
import com.example.savushkin.domain.usecases.CheckAuthUseCase
import com.example.savushkin.domain.usecases.GetAllRequestsUseCase
import com.example.savushkin.domain.usecases.GetCorrectLoginUseCase
import com.example.savushkin.domain.usecases.GetCorrectPasswordUseCase
import com.example.savushkin.domain.usecases.GetCountInDirectoryUseCase
import com.example.savushkin.domain.usecases.GetCountProductsOfRequestsUseCase
import com.example.savushkin.domain.usecases.GetCountRequestUseCase
import com.example.savushkin.domain.usecases.GetProductOfDirectoryByCodeUseCase
import com.example.savushkin.domain.usecases.GetProductsOfDirectoryUseCase
import com.example.savushkin.domain.usecases.GetProductsOfRequestUseCase
import com.example.savushkin.domain.usecases.GetRequestByNumberUseCase
import com.example.savushkin.domain.usecases.GetStatusRememberEnterUseCase
import com.example.savushkin.domain.usecases.SetStatusRememberEnterUseCase
import com.example.savushkin.domain.usecases.UpdateQuantityOfProductUseCase
import com.example.savushkin.domain.usecases.UpdateRequestStatusUseCase
import com.journeyapps.barcodescanner.ScanIntentResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val requestsList : LiveData<List<Request>>
    val directoryList : LiveData<List<Directory>>
    private var myRep : MyRepositoryImpl

    private lateinit var currentRequest : Request

    // СОСТОЯНИЯ АВТОРИЗАЦИИ
    private var stateLogin by mutableStateOf("")
    private var statePassword by mutableStateOf("")
    private var stateStatusRememberEnter by mutableStateOf(false)

    // СОСТОЯНИЯ ЗАЯВКИ
    private var stateNumberRequest by mutableLongStateOf(0)
    private var stateStatusAllScan by mutableStateOf(true)

    // USECASE-ы
    private val checkAuthUseCase: CheckAuthUseCase
    private val getStatusRememberEnterUseCase: GetStatusRememberEnterUseCase
    private val setStatusRememberEnterUseCase: SetStatusRememberEnterUseCase
    private val getCorrectLoginUseCase: GetCorrectLoginUseCase
    private val getCorrectPasswordUseCase: GetCorrectPasswordUseCase
    private val getCountInDirectoryUseCase: GetCountInDirectoryUseCase
    private val getAllRequestsUseCase: GetAllRequestsUseCase
    private val getProductsOfDirectoryUseCase: GetProductsOfDirectoryUseCase
    private val addRequestsUseCase: AddRequestUseCase
    private val getCountRequestUseCase: GetCountRequestUseCase
    private val addProductInDirectoryUseCase: AddProductInDirectoryUseCase
    private val getRequestByNumberUseCase: GetRequestByNumberUseCase
    private val getProductsOfRequestUseCase: GetProductsOfRequestUseCase
    private val getProductOfDirectoryByCodeUseCase: GetProductOfDirectoryByCodeUseCase
    private val getCountProductsOfRequestsUseCase: GetCountProductsOfRequestsUseCase
    private val addProductOfRequestUseCase: AddProductOfRequestUseCase
    private val updateQuantityOfProductUseCase: UpdateQuantityOfProductUseCase
    private val updateRequestStatusUseCase: UpdateRequestStatusUseCase

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
        addRequestsUseCase = AddRequestUseCase(myRep = myRep)
        getCountRequestUseCase = GetCountRequestUseCase(myRep = myRep)
        addProductInDirectoryUseCase = AddProductInDirectoryUseCase(myRep = myRep)
        getRequestByNumberUseCase = GetRequestByNumberUseCase(myRep = myRep)
        getProductsOfRequestUseCase = GetProductsOfRequestUseCase(myRep = myRep)
        getProductOfDirectoryByCodeUseCase = GetProductOfDirectoryByCodeUseCase(myRep = myRep)
        getCountProductsOfRequestsUseCase = GetCountProductsOfRequestsUseCase(myRep = myRep)
        addProductOfRequestUseCase = AddProductOfRequestUseCase(myRep = myRep)
        updateQuantityOfProductUseCase = UpdateQuantityOfProductUseCase(myRep = myRep)
        updateRequestStatusUseCase = UpdateRequestStatusUseCase(myRep = myRep)


        if(getStatusRememberEnterUseCase.execute()) {
            stateLogin = getCorrectLoginUseCase.execute()
            statePassword = getCorrectPasswordUseCase.execute()
            stateStatusRememberEnter = getStatusRememberEnterUseCase.execute()
        }

        runBlocking {
            if(getCountInDirectoryUseCase.execute() == 0) {
                loadDirectory()
            }

            if(getCountRequestUseCase.execute() == 0) {
                loadRequests()
            }

            if(getCountProductsOfRequestsUseCase.execute() == 0) {
                loadProducts()
            }

        }

        requestsList = getAllRequestsUseCase.execute()
        directoryList = getProductsOfDirectoryUseCase.execute()


    }

    private fun loadDirectory() {
        viewModelScope.launch {
            val directoryList = getDirectoryFromXML()
            directoryList.forEach {
                addProductInDirectoryUseCase.execute(it)
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
                val gradus = directoryObject.get("GRADUS")?.value ?: ""
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

    private fun loadRequests() {
        viewModelScope.launch {
            val requestsList = getRequestsFromXML()
            requestsList.forEach{
                addRequestsUseCase.execute(it)
            }
        }
    }

    private fun getRequestsFromXML() : List<Request> {
        val xmlFile = "requests.xml"
        val assetManager = getApplication<Application>().assets
        val inputStream = assetManager.open(xmlFile)
        val xmlObject = XMLParser.parse(inputStream)
        val requestsList = ArrayList<Request>()

        xmlObject.descendants?.forEach { requestObject ->
            if (requestObject.tag == "ZPL") {
                val numberRequest = (requestObject.get("SYSN")?.value ?: "").toLong()
                val date = (requestObject.get("DT")?.value ?: "").formatDate()
                val nameShop = requestObject.get("NAMESHOP")?.value ?: ""
                println(nameShop)
                val address = requestObject.get("ADDRESS")?.value ?: ""
                requestsList.add(Request(
                    numberRequest = numberRequest,
                    dateRequest = date,
                    nameShop = nameShop,
                    addressShop = address,
                    statusRequest = "НОВАЯ"
                ))
            }
        }
        return requestsList
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val productsList = getProductsOfRequestsFromXML()
            productsList.forEach{
                addProductOfRequestUseCase.execute(it)
            }
        }
    }

    private fun getProductsOfRequestsFromXML() : List<ProductOfRequest> {
        val xmlFile = "products.xml"
        val assetManager = getApplication<Application>().assets
        val inputStream = assetManager.open(xmlFile)
        val xmlObject = XMLParser.parse(inputStream)
        val productsList = ArrayList<ProductOfRequest>()

        xmlObject.descendants?.forEach { productObject ->
            if (productObject.tag == "NPC") {
                val numberRequest = (productObject.get("SYSNZPL")?.value ?: "").toLong()
                val codeProduct = productObject.get("KMC")?.value ?: ""
                val quantity = (productObject.get("KOLM")?.value ?: "").toInt()
                productsList.add(ProductOfRequest(
                    codeProduct = codeProduct,
                    quantity = quantity,
                    numberRequest = numberRequest
                ))
            }
        }
        return productsList
    }

    private fun String.formatDate(): String {
        return if (this.length == 8) {
            "${this.substring(0, 2)}.${this.substring(2, 4)}.${this.substring(4, 8)}"
        } else {
            this
        }
    }

    fun checkAuth(navigateToAllRequestsPage : () -> Unit) {
        if(checkAuthUseCase.execute(login = stateLogin, password = statePassword)) {
            setStatusRememberEnterUseCase.execute(stateStatusRememberEnter)
            navigateToAllRequestsPage()
        }
        else {
            val context = getApplication<Application>().applicationContext
            val toast = Toast.makeText(context, "Введён неверный Логин и/или Пароль", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 150)
            toast.show()
        }
    }

    fun getNumberRequest() : Long {
        return stateNumberRequest
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

    fun getRequestByNumber(number : Long) : Request {
        return runBlocking {
            getRequestByNumberUseCase.execute(number = number)
        }
    }

    fun getProductsOfRequest(number: Long) : List<ProductOfRequest> {
        return runBlocking {
            getProductsOfRequestUseCase.execute(numberRequest = number)
        }
    }

    fun getProductOfDirectoryByCode(code : String) : List<Directory> {
        return runBlocking {
            getProductOfDirectoryByCodeUseCase.execute(code = code)
        }

    }

    fun getStatusAllScan() : Boolean {
        return stateStatusAllScan
    }

    fun setNumberRequest(newNumberRequest : Long) {
        stateNumberRequest = newNumberRequest
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

    fun setStatusAllScan(newStatus : Boolean) {
        stateStatusAllScan = newStatus
    }

    fun setCurrentRequest(request : Request) {
        currentRequest = request
    }

    fun scanResult(result : ScanIntentResult) {
        val context = getApplication<Application>().applicationContext
        if(result.contents == null) {
            val toast = Toast.makeText(context, "НИЧЕГО НЕ ПРОСКАНИРОВАНО", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.show()
        }
        else {
            val productsOfRequest = getProductsOfRequest(currentRequest.numberRequest)
            var isCorrect = false
            productsOfRequest.forEach {
                val productOfDirectory = getProductOfDirectoryByCode(it.codeProduct)
                if(productOfDirectory[0].ean13 == result.contents && it.quantity != 0) {
                    isCorrect = true
                    runBlocking {
                        updateRequestStatusUseCase.execute(
                            numberRequest = it.numberRequest,
                            newStatusRequest = "В ПРОЦЕССЕ"
                        )

                        updateQuantityOfProductUseCase.execute(
                            codeProduct = it.codeProduct,
                            newQuantity = it.quantity - 1,
                            numberRequest = it.numberRequest
                        )
                    }
                }
            }
            // 4810268012181
            if(!isCorrect) {
                val toast = Toast.makeText(context, "ОШИБКА", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
        }
    }
}
package com.bsuir.kovko.app.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.kovko.Singletons
import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import com.bsuir.kovko.app.repository.HomeRepository
import com.bsuir.kovko.app.utils.MutableLiveEvent
import com.bsuir.kovko.app.utils.MutableUnitLiveEvent
import com.bsuir.kovko.app.utils.publishEvent
import com.bsuir.kovko.app.utils.share
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val homeRepository: HomeRepository = Singletons.apiRepository,
): ViewModel() {

    private val _message = MutableLiveEvent<String>()
    val message = _message.share()

    private val _warehouseName = MutableLiveData<List<String>>()
    val warehouseName = _warehouseName.share()

    private val _apps = MutableLiveData<List<ApplicationAnswerDTO>>()
    val apps = _apps.share()

    private val _app = MutableLiveData<ApplicationAnswerDTO>()
    val app = _app.share()

    private val _navigateToTabsEvent = MutableUnitLiveEvent()
    val navigateToTabsEvent = _navigateToTabsEvent.share()

    fun getRole(): String {
        return homeRepository.getRole()!!
    }

    private fun showToast(mes: String) = _message.publishEvent(mes)

    fun getUsername(): String {
        return homeRepository.getUsername()!!
    }

    fun getWarehouseName() {
        viewModelScope.launch {
            var res: Response<List<String>> = homeRepository.getWarehouseName()
            if (res.isSuccessful){
                _warehouseName.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun createWarehouse(warehouseDTO: WarehouseDTO) {
        viewModelScope.launch {
            var res: Response<HttpResponse> = homeRepository.createWarehouse(warehouseDTO)
            if (res.isSuccessful){
                showToast(res.body()!!.message)
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun createSubject(subjectDTO: SubjectDTO) {
        viewModelScope.launch {
            var res: Response<List<ApplicationAnswerDTO>> = homeRepository.createSubject(subjectDTO)
            if (res.isSuccessful){
                _apps.value = res.body()
                showToast("Предмет успешно добавлен на склад!")
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun getApplications() {
        viewModelScope.launch {
            var res: Response<List<ApplicationAnswerDTO>> = homeRepository.getApplications()
            if (res.isSuccessful){
                _apps.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun pickUpSubject(number: String) {
        viewModelScope.launch {
            var res: Response<List<ApplicationAnswerDTO>> = homeRepository.pickUpSubject(number)
            if (res.isSuccessful){
                _apps.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

    fun getApplication(id: Long) {
        viewModelScope.launch {
            var res: Response<ApplicationAnswerDTO> = homeRepository.getApplication(id)
            if (res.isSuccessful){
                _app.value = res.body()
            } else {
                val gson = GsonBuilder().setDateFormat("MM-dd-yyyy hh:mm:ss").create()
                val mes = gson.fromJson(res.errorBody()!!.string(), HttpResponse::class.java).message
                showToast(mes)
            }
        }
    }

}
package com.bsuir.kovko.sources.model.home

import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import com.bsuir.kovko.sources.backend.BackendRetrofitSource
import com.bsuir.kovko.sources.backend.RetrofitConfig
import kotlinx.coroutines.delay
import retrofit2.Response

class RetrofitApiSource(
    config: RetrofitConfig
) : BackendRetrofitSource(config), HomeSource {

    private val homeApi = retrofit.create(HomeApi::class.java)

    override suspend fun getWarehouseName(): Response<List<String>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getWarehouseName()
    }

    override suspend fun createWarehouse(warehouseDTO: WarehouseDTO): Response<HttpResponse> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.createWarehouse(warehouseDTO)
    }

    override suspend fun createSubject(subjectDTO: SubjectDTO): Response<List<ApplicationAnswerDTO>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.createSubject(subjectDTO)
    }

    override suspend fun getApplications(): Response<List<ApplicationAnswerDTO>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getApplications()
    }

    override suspend fun pickUpSubject(number: String): Response<List<ApplicationAnswerDTO>> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.pickUpSubject(number)
    }

    override suspend fun getApplication(id: Long): Response<ApplicationAnswerDTO> = wrapRetrofitExceptions {
        delay(1000)
        homeApi.getApplication(id)
    }


}
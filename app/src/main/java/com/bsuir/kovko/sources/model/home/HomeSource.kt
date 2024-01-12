package com.bsuir.kovko.sources.model.home

import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import retrofit2.Response

interface HomeSource {

    suspend fun getWarehouseName(): Response<List<String>>
    suspend fun createWarehouse(warehouseDTO: WarehouseDTO): Response<HttpResponse>
    suspend fun createSubject(subjectDTO: SubjectDTO): Response<List<ApplicationAnswerDTO>>
    suspend fun getApplications(): Response<List<ApplicationAnswerDTO>>
    suspend fun pickUpSubject(number: String): Response<List<ApplicationAnswerDTO>>
    suspend fun getApplication(id: Long): Response<ApplicationAnswerDTO>

}
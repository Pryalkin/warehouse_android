package com.bsuir.kovko.sources.model.home

import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeApi {

    @GET("home/warehouse/name")
    suspend fun getWarehouseName(): Response<List<String>>

    @POST("home/warehouse/create")
    suspend fun createWarehouse(@Body warehouseDTO: WarehouseDTO): Response<HttpResponse>

    @POST("home/subject/create")
    suspend fun createSubject(@Body subjectDTO: SubjectDTO): Response<List<ApplicationAnswerDTO>>

    @GET("home/application/get")
    suspend fun getApplications(): Response<List<ApplicationAnswerDTO>>

    @POST("home/application/pick_up")
    suspend fun pickUpSubject(@Query("number") number: String): Response<List<ApplicationAnswerDTO>>

    @POST("home/application/id")
    suspend fun getApplication(@Query("id") id: Long): Response<ApplicationAnswerDTO>
}
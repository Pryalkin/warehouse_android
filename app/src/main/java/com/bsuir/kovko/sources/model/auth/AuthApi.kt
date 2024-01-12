package com.bsuir.kovko.sources.model.auth

import com.bsuir.kovko.app.dto.UserDTO
import com.bsuir.kovko.app.dto.answer.LoginUserAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/registration")
    suspend fun registration(@Body userDTO: UserDTO): Response<HttpResponse>

    @POST("auth/login")
    suspend fun login(@Body userDTO: UserDTO): Response<LoginUserAnswerDTO>

}
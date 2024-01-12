package com.bsuir.kovko.sources.model.auth

import com.bsuir.kovko.app.dto.UserDTO
import com.bsuir.kovko.app.dto.answer.LoginUserAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import retrofit2.Response

interface AuthSource {
    suspend fun registration(userDTO: UserDTO): Response<HttpResponse>
    suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO>
}
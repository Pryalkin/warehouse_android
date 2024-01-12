package com.bsuir.kovko.sources.model.auth

import com.bsuir.kovko.app.dto.UserDTO
import com.bsuir.kovko.app.dto.answer.LoginUserAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import com.bsuir.kovko.sources.backend.BackendRetrofitSource
import com.bsuir.kovko.sources.backend.RetrofitConfig
import kotlinx.coroutines.delay
import retrofit2.Response

class RetrofitAuthSource(
    config: RetrofitConfig
) : BackendRetrofitSource(config), AuthSource {

    private val authApi = retrofit.create(AuthApi::class.java)

    override suspend fun registration(userDTO: UserDTO): Response<HttpResponse> = wrapRetrofitExceptions {
        delay(1000)
        authApi.registration(userDTO)
    }

    override suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO>  = wrapRetrofitExceptions {
        delay(1000)
        authApi.login(userDTO)
    }


}
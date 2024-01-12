package com.bsuir.kovko.app.repository

import com.bsuir.kovko.app.dto.UserDTO
import com.bsuir.kovko.app.dto.answer.LoginUserAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import com.bsuir.kovko.app.setting.AppSettings
import com.bsuir.kovko.sources.exception.BackendException
import com.bsuir.kovko.sources.exception.InvalidCredentialsException
import com.bsuir.kovko.sources.model.auth.AuthSource
import retrofit2.Response

class AuthRepository(
    private val authSource: AuthSource,
    private val appSettings: AppSettings
) {

    suspend fun login(userDTO: UserDTO): Response<LoginUserAnswerDTO> {
        val res: Response<LoginUserAnswerDTO> = try {
            authSource.login(userDTO)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
        appSettings.setCurrentToken(res.headers().get("Jwt-Token"))
        appSettings.setCurrentUsername(res.body()?.username)
        appSettings.setCurrentRole(res.body()?.role)
        return res
    }

    suspend fun registration(userDTO: UserDTO): Response<HttpResponse> {
        val res: Response<HttpResponse> = try {
            authSource.registration(userDTO)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
        return res
    }

    fun logout(){
        appSettings.setCurrentToken("")
        appSettings.setCurrentUsername("")
        appSettings.setCurrentRole("")
    }

}
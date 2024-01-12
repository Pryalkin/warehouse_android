package com.bsuir.kovko.app.repository

import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.HttpResponse
import com.bsuir.kovko.app.setting.AppSettings
import com.bsuir.kovko.sources.exception.BackendException
import com.bsuir.kovko.sources.exception.InvalidCredentialsException
import com.bsuir.kovko.sources.model.home.HomeSource
import retrofit2.Response

class HomeRepository(
    private val homeSource: HomeSource,
    private val appSettings: AppSettings
) {

    fun getRole(): String?{
        return appSettings.getCurrentRole()
    }

    fun getUsername(): String? {
        return appSettings.getCurrentUsername()
    }

    suspend fun getWarehouseName(): Response<List<String>> {
        val res: Response<List<String>> = try {
            homeSource.getWarehouseName()
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

    suspend fun createWarehouse(warehouseDTO: WarehouseDTO): Response<HttpResponse> {
        val res: Response<HttpResponse> = try {
            homeSource.createWarehouse(warehouseDTO)
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

    suspend fun createSubject(subjectDTO: SubjectDTO): Response<List<ApplicationAnswerDTO>> {
        val res: Response<List<ApplicationAnswerDTO>> = try {
            homeSource.createSubject(subjectDTO)
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

    suspend fun getApplications(): Response<List<ApplicationAnswerDTO>> {
        val res: Response<List<ApplicationAnswerDTO>> = try {
            homeSource.getApplications()
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

    suspend fun pickUpSubject(number: String): Response<List<ApplicationAnswerDTO>> {
        val res: Response<List<ApplicationAnswerDTO>> = try {
            homeSource.pickUpSubject(number)
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

    suspend fun getApplication(id: Long): Response<ApplicationAnswerDTO> {
        val res: Response<ApplicationAnswerDTO> = try {
            homeSource.getApplication(id)
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

}